#final step of proof for birthday paradox
#Specific case- if 23 people are in a room, the probability
#that at least 2 people share a birthday is 1/2.

product = 1.0

for i in range(23):
	x = (365 - i) / 365.0 #the probability that i people dont share a birthday
	print x
	product = product * x

print 1-product
