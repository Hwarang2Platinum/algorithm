import Foundation

final class Queue<T> {
    private var q = [T]()
    private var i = 0

    var isEmpty: Bool {
        return i >= q.count
    }

    func push(_ elem: T) {
        q.append(elem)
    }

    func pop() -> T {
        defer { i += 1 }
        return q[i]
    }
}

let line = readLine()!.split{$0 == " "}.map{Int($0)!}
let N = line[0]
let M = line[1]
let K = line[2]
var li = Array(repeating: Array(repeating: 0, count: M), count: N)

for _ in 0..<K {
    let line = readLine()!.split{$0 == " "}.map{Int($0)!}
    for j in line[0]..<line[2] {
        for i in line[1]..<line[3] {
            li[i][j] = 1
        }
    }
}

var isv = Array(repeating: Array(repeating: false, count: M), count: N)
let ix = [0,0,1,-1]
let iy = [1,-1,0,0]

func bfs(_ a: Int, _ b: Int) -> Int {
    var ret = 0
    let q = Queue<(Int, Int)>()
    q.push((a, b))
    li[a][b] = 1
    isv[a][b] = true
    while !q.isEmpty {
        let (x, y) = q.pop()
        ret += 1
        for i in 0..<4 {
            let X = x + ix[i]
            let Y = y + iy[i]

            if X < 0 || Y < 0 || X >= N || Y >= M || isv[X][Y] || li[X][Y] != 0 { continue }
            li[X][Y] = 1
            isv[X][Y] = true
            q.push((X, Y))
        }
    }

    return ret
}

var ans = 0
var ansli = [Int]()

for i in 0..<N {
    for j in 0..<M where li[i][j] == 0 && !isv[i][j] {
        ansli.append(bfs(i, j))
        ans += 1
    }
}

print(ans)
print(ansli.sorted(by: <).reduce("", {$0 + "\($1) "}))
