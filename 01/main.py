file = open('input.txt', 'rb')
f = file.readlines() 
file.close()

ints = []
for i in f:
    ints.append(int(i))

windows = []
win_a = 0
win_b = 0 
win_c = 0
win_d = 0 
counter = 0
for i in ints:
    if counter == 0:
        win_a += i
        if win_c != 0:
            win_c += i 
            print("c: " + str(win_c))
            windows.append(win_c)
            win_c = 0
        if win_d != 0:
            win_d += i
    elif counter == 1:
        win_a += i
        win_b += i
        if win_d != 0:
            win_d += i
            print("d: " + str(win_d))
            windows.append(win_d)
            win_d = 0
    elif counter == 2:
        win_a += i
        print("a: " + str(win_a))
        windows.append(win_a)
        win_a = 0
        win_b += i
        win_c += i
    elif counter == 3:
        win_b += i
        print("b: " + str(win_b))
        windows.append(win_b)
        win_b = 0
        win_c += i
        win_d += i
        counter = -1
    counter += 1

for i in windows:
    print(str(i))



high = 0
prev = 0
for i in windows:
    if i > prev:
        high += 1
    prev = i
high -= 1
print("part 2: " + str(high))


high = 0
prev = 0
for i in ints:
    if int(i) > prev:
        high += 1 
    prev = int(i)

# decrease high because the first comparison with 0 does not count
high -= 1

print("part 1: " + str(high))

