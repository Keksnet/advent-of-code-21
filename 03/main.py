file = open("input.txt", "r")
lines = file.readlines()
file.close()

def remove_mosts(l_ist, j, i, one, zero, prefer, p1 = "0", p2 = "1"):
    if one > zero:
        if j[i] == p1:
            if not j in l_ist:
                return
            if len(l_ist) > 1:
                l_ist.remove(j)
    elif zero > one:
        if j[i] == p2:
            if not j in l_ist:
                return
            if len(l_ist) > 1:
                l_ist.remove(j)
    elif one == zero:
        if prefer == "1":
            if j[i] == "0":
                if not j in l_ist:
                    return
                if len(l_ist) > 1:
                    l_ist.remove(j)
        else:
            if j[i] == "1":
                if not j in l_ist:
                    return
                if len(l_ist) > 1:
                    l_ist.remove(j)

def find_one_zero(index ,elems):
    one = 0
    zero = 0
    for i in elems:
        h = i[index]
        if h == "1":
            one += 1
        else:
            zero += 1
    return one, zero

a = ""
b = ""

most = lines.copy()

for i in range(len(lines[0]) - 1):
    one, zero = find_one_zero(i, lines)
    if one > zero:
        print("1: " + str(one) + " : " + str(zero))
        a += "1"
        b += "0"
    else:
        print("2: " + str(one) + " : " + str(zero))
        a += "0"
        b += "1"
    one, zero = find_one_zero(i, most)
    print(str(one) + " : " + str(zero))
    for j in lines:
        remove_mosts(most, j, i, one, zero, prefer="1", p1="0", p2="1")

least = lines.copy()

for i in range(len(lines[0]) - 1):
    one, zero = find_one_zero(i, least)
    for j in lines:
        remove_mosts(least, j, i, one, zero, prefer="0", p1="1", p2="0")
    print(least)

f_a = int(a, 2)
f_b = int(b, 2)

print(least[0])

f_most = int(most[0], 2)
f_least = int(least[0], 2)

print("p1: a: " + str(f_a) + "\n b: " + str(f_b) + "\n mul: " + str(f_a * f_b))
print("p2: most: " + str(f_most) + "\n least: " + str(f_least) + "\n mul: " + str(f_most * f_least))
