#final step of proof for birthday paradox

product = 1.0

for i in range(23):
	x = (365 - i) / 365.0
	print x
	product = product * ((365 - i) / 365.0)

print 1 - product
