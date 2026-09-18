class Solution(object):
    def maxNumOfSubstrings(self, s):
        first = {}
        last = {}
        for idx, ch in enumerate(s):
            if ch not in first:
                first[ch] = idx
            last[ch] = idx
        
        valid_intervals = []
        
        for ch in set(s):
            L = first[ch]
            R = last[ch]
            is_valid = True
            
            i = L
            while i <= R:
                if first[s[i]] < L:
                    is_valid = False
                    break
                R = max(R, last[s[i]])
                i += 1
            
            if is_valid:
                valid_intervals.append((L, R))
        
        valid_intervals.sort(key=lambda x: x[1])
        
        ans = []
        prev_end = -1
        
        for L, R in valid_intervals:
            if L > prev_end:
                ans.append(s[L : R + 1])
                prev_end = R
                
        return ans