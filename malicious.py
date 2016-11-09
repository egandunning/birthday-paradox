#downloads a file

import urllib2

response = urllib2.urlopen('https://cdn.rawgit.com/egandunning/birthday-paradox/master/README.md')
print response.read()
