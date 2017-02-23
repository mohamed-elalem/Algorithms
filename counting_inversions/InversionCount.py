
def sort(x, lo, mid, hi):
    inversions = 0
    n1 = mid - lo + 1
    n2 = hi - mid
    
    l = []
    r = []
    
    for i in range(0, n1):
        l.append(x[lo + i])
    
    for i in range(0, n2):
        r.append(x[mid + i + 1])
    
    i = 0
    j = 0
    k = lo
    
    while i < n1 and j < n2:
        if l[i] <= r[j]:
            x[k] = l[i]
            i += 1
        else:
            x[k] = r[j]
            j += 1
            inversions += (n1 - i)
        k += 1
           
    while i < n1:
        x[k] = l[i]
        i += 1
        k += 1
    while j < n2:
        x[k] = r[j]
        j += 1
        k += 1
    return inversions
    
def merge_sort(x, lo, hi):
    inversions = 0
    if hi - lo > 0:
        mid = (lo + hi) // 2
        inversions += merge_sort(x, lo, mid)
        inversions += merge_sort(x, mid + 1, hi)
        inversions += sort(x, lo, mid, hi)
    return inversions

t = int(input())
while t > 0:
    input()
    t -= 1
    n = int(input())
    x = []
    for i in range(0, n):
        x.append(int(input()))
    print(merge_sort(x, 0, n - 1))
    
    