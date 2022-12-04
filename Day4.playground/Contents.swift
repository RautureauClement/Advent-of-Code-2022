import Foundation

var cptRangeInRange = 0
var cptRangeOverlap = 0

if let path = Bundle.main.path(forResource: "input", ofType: "txt") {
    do {
        let data = try String(contentsOfFile: path, encoding: .utf8)
        let myStrings = data.components(separatedBy: .newlines)
        
        for line in myStrings {
            if line.isEmpty {
                continue
            }
            
            let pairs = line.split(separator: ",")
            let pair1 = pairs[0].split(separator: "-")
            let range1 = Int(pair1[0])!...Int(pair1[1])!
            let pair2 = pairs[1].split(separator: "-")
            let range2 = Int(pair2[0])!...Int(pair2[1])!
            
            if range1.clamped(to: range2) == range1 || range2.clamped(to: range1) == range2 {
                cptRangeInRange += 1
            }
            
            if range1.overlaps(range2) {
                cptRangeOverlap += 1
            }
            
        }
        
        print("output 1 : \(cptRangeInRange)")
        print("output 2 : \(cptRangeOverlap)")
        
    } catch {
        print(error)
    }
}
