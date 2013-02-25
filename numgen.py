import random
import sys

N=int(sys.argv[1])
i=0
f=open('randList.txt','w')

while i<N:
    number= random.randrange(1,9999999)
    f.write(str(number)+'\n')
    i=i+1
