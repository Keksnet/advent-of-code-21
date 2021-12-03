file = open("input.txt", "rb")
f = file.readlines()
file.close()



heigth = 0
depth = 0


for i in f:
    s = i.split()
    print(s)
    if s[0].decode() == "forward":
        heigth += int(s[1].decode())
    elif s[0].decode() == "down":
        depth += int(s[1].decode())
    elif s[0].decode() == "up":
        depth -= int(s[1].decode())

print(heigth)
print(depth)

print("1: " + str(heigth * depth))


hori = 0
depth = 0
aim = 0

for i in f:
    s = i.split()
    print(hori)
    print(depth)
    print(aim)
    print()
    if s[0].decode() == "forward":
        depth += aim * int(s[1].decode())
        hori += int(s[1].decode())
    elif s[0].decode() == "down":
        aim += int(s[1].decode())
    elif s[0].decode() == "up":
        aim -= int(s[1].decode())

print(hori)
print(depth)
print(aim)

print("2: " + str(hori * depth))


