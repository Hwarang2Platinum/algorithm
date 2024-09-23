import Foundation

func solution(_ m:Int, _ n:Int, _ x:Int, _ y:Int, _ balls:[[Int]]) -> [Int] {
    balls.map {
        let (a, b) = ($0[0], $0[1])
        
        let p = (x+a)*(x+a) + (b-y)*(b-y)
        let q = (a-x)*(a-x) + (2*n-y-b)*(2*n-y-b)
        let c = (2*m-x-a)*(2*m-x-a) + (b-y)*(b-y)
        let t = (a-x)*(a-x) + (b+y)*(b+y)
        
        if a == x {
            return min(min(p,c), b > y ? t : q)
        } else if b == y {
            return min(min(q,t), a > x ? p : c)
        }
        return min(min(p,q),min(c,t))
    }
}