import axios from 'axios';
import dotenv from 'dotenv';
import OpenAI from 'openai';
import * as cheerio from 'cheerio';

dotenv.config();
const openai = new OpenAI({ apiKey: process.env.GPT_TOKEN });

/**
 * IMP : axios와 User-Agent를 사용하여 문제 정보를 가져옴
 * IMP : cheerio를 사용하여 id 기반으로 정보를 가져와서, 정제함
 * IMP : OpenAI API를 사용하여 GPT-4를 사용하여 힌트를 생성함
 */

/**
 * Type Usage : getProblemHint(16932, '모양 만들기', '그래프 이론');
 * @param {*} problemId
 * @param {*} problemTitle
 * @param {*} problemType
 * @returns
 */
export const getProblemHint = async (problemId, problemTitle, problemType) => {
  try {
    const response = await axios.get(`https://www.acmicpc.net/problem/${problemId}`, {
      headers: {
        'User-Agent':
          'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
      },
    });

    const html = response.data;
    const $ = cheerio.load(html);

    const parseFormula = (element) => {
      let result = '';
      element.contents().each((index, child) => {
        if (child.type === 'text')
          result += $(child).text().replace(/\t+/g, '').replace(/\n\n+/g, '\n');
        else if (child.tagName === 'sub') result += `_${$(child).text().trim()}`;
        else if (child.tagName === 'sup') result += `^${$(child).text().trim()}`;
        else result += parseFormula($(child));
      });
      return result.trim();
    };

    const description = parseFormula($('#problem_description'));
    const input = parseFormula($('#problem_input'));
    const output = parseFormula($('#problem_output'));

    const limit = [];
    $('#problem_limit ul li').each((index, element) => {
      limit.push(parseFormula($(element)));
    });
    const timeLimit = $('#problem-info tbody tr td:nth-child(1)').text().trim();
    const memoryLimit = $('#problem-info tbody tr td:nth-child(2)').text().trim();
    limit.push(`시간 제한: ${timeLimit}`, `메모리 제한: ${memoryLimit}`);

    const samples = [];
    $('pre.sampledata').each((index, element) => {
      const sampleId = $(element).attr('id');
      const sampleText = $(element).text();
      samples.push({ id: sampleId, data: sampleText });
    });

    const problemData = {
      title: problemTitle,
      description,
      input,
      output,
      limit,
      tags: problemType,
      samples: samples.map((sample) => {
        const type = sample.id.includes('input') ? 'sample-input' : 'sample-output';
        const number = sample.id.match(/\d+/)[0];
        return {
          type: `${type}-${number}`,
          data: sample.data,
        };
      }),
    };

    const gptResponse = await openai.chat.completions.create({
      model: 'gpt-4',
      messages: [
        {
          role: 'system',
          content: `You are an AI assistant that provides hints and Java logic code for solving algorithm problems. Always use the following template for your response:
          
### **문제 해결 방법**

### 📋 **문제 설명**
<Problem Description>

### 💡 **문제 해결 힌트**
1️⃣ <Step 1>
2️⃣ <Step 2>
...

### 🧩 **주요 Logic Code**
\`\`\`<Language>
<Logic Code>
\`\`\`
          `,
        },
        {
          role: 'user',
          content: `Here is the problem data in JSON format:\n\n${JSON.stringify(
            problemData,
            null,
            2
          )}\n\nPlease provide the response in the requested format.`,
        },
      ],
    });
    // Step 3: GPT의 응답 반환
    return gptResponse.choices[0].message.content;
  } catch (error) {
    console.error(error);
  }
};
