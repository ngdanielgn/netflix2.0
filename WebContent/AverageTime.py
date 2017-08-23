count = 0;
total = 0;

str = raw_input("Enter file path: ")
with open(str) as f:
    content = f.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line
content = [x.strip() for x in content] 

for num in content:
	total += int(num)

average = total/len(content)
print(average)