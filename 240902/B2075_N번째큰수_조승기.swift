import Foundation

final class FileIO {
    private let buffer:[UInt8]
    private var index: Int = 0

    init(fileHandle: FileHandle = FileHandle.standardInput) {
        
        buffer = Array(try! fileHandle.readToEnd()!)+[UInt8(0)] // 인덱스 범위 넘어가는 것 방지
    }

    @inline(__always) private func read() -> UInt8 {
        defer { index += 1 }

        return buffer[index]
    }

    @inline(__always) func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true

        while now == 10
                || now == 32 { now = read() } // 공백과 줄바꿈 무시
        if now == 45 { isPositive.toggle(); now = read() } // 음수 처리
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now-48)
            now = read()
        }

        return sum * (isPositive ? 1:-1)
    }

    @inline(__always) func readString() -> String {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시
        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return String(bytes: Array(buffer[beginIndex..<(index-1)]), encoding: .ascii)!
    }

    @inline(__always) func readByteSequenceWithoutSpaceAndLineFeed() -> [UInt8] {
        var now = read()

        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시
        let beginIndex = index-1

        while now != 10,
              now != 32,
              now != 0 { now = read() }

        return Array(buffer[beginIndex..<(index-1)])
    }
}


struct Heap<T> {
    private var li = [T]()
    private var compare: (T, T) -> Bool
    init(compare: @escaping (T, T) -> Bool) {
        self.compare = compare
    }
    
    var size: Int {
        li.count
    }
    
    func p(_ n: Int) -> Int{
        (n-1)/2
    }
    
    func l(_ n: Int) -> Int {
        (n*2)+1
    }
    
    func r(_ n: Int) -> Int {
        (n+1)*2
    }
    
    func top() -> T {
        li[0]
    }
    
    mutating func push(_ elem: T) {
        li.append(elem)
        var point = li.count-1
        
        while point > -1 {
            if compare(li[point], li[p(point)]) {
                li.swapAt(point, p(point))
                point = p(point)
            } else {
                return
            }
        }
    }
    
    mutating func pop() -> T {
        li.swapAt(0, li.count-1)
        let ret = li.removeLast()
        var point = 0
        
        while r(point) < li.count {
            if compare(li[l(point)], li[r(point)]) {
                if compare(li[l(point)], li[point]) {
                    li.swapAt(l(point), point)
                    point = l(point)
                    continue
                } else {
                    break
                }
            } else {
                if compare(li[r(point)], li[point]) {
                    li.swapAt(r(point), point)
                    point = r(point)
                    continue
                } else {
                    break
                }
            }
        }
        
        if l(point) == li.count - 1,
           compare(li[l(point)], li[point]) {
            li.swapAt(l(point), point)
        }
        return ret
    }
}

let fio = FileIO()
let n = fio.readInt()
var q = Heap<Int>(compare: <)
for i in 0..<n {
    for j in 0..<n {
        q.push(fio.readInt())
        if (q.size > n) {
            _=q.pop()
        }
    }
}


print(q.top())
