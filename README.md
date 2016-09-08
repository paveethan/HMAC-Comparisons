## Synopsis 

This program was written to compare various HMAC implementations (SHA-1, MD5, AES). It was originally written for course MTH-816 (Cryptography). 

## Implementation

The implementation is relatively straight-forward, it uses a total of 6 functions, 2 for each various HMAC.
For the SHA-1 HMAC, there’s two functions, the hmacSHA1 function hashes the given input and appends the resulting hash onto the original message and returns that, then it is sent to the verifyHMACSHA1 function where the input and the hash is extracted, and the input is then hashed again through the HMACSHA1 function. The resulting string’s hash is then extracted and compared to the hash value of the first extracted hash. If they match, then a boolean value of true is returned.

For the MD5 HMAC, there’s two functions, the hmacMD5 function hashes the given input and appends the resulting hash onto the original message and returns that, then it is sent to the verifyHMACMD5 function where the input and the hash is extracted, and the input is then hashed again through the HMACMD5 function. The resulting string’s hash is then extracted and compared to the hash value of the first extracted hash. If they match, then a boolean value of true is returned.

For the AES (ECB mode with PKCS5 padding) HMAC, there’s two functions, the hmacAES function hashes the given input and appends the resulting hash onto the original message and returns that, then it is sent to the verifyHMACAES function where the input and the hash is extracted, and the input is then hashed again through the HMACAES function. The resulting string’s hash is then extracted and compared to the hash value of the first extracted hash. If they match, then a boolean value of true is returned.

The times are calculated in nanoseconds, and is calculated by getting the current time in nanoseconds before running the hmac function, and after running the hmac function and then calculating the difference, it is then outputted to the console, among the hash, and whether the hmac function had succeeded or not in running.


## Results 


The results of the testing is shown in the table below with additional explanation following after.

	
SHA-1
	
MD5	
AES

Speed 1: 	
35,807,843
	
1,048,993	
680,176,851

Speed 2:
	
37,001,481	
1,349,803	
764,797,744

Speed 3:
	
38,664,252	
1,005,472	
695,674,296

Average Speed:
	
37,157,858.66666667	
1,134,756	
713,549,630.3333333

Hash:
	
nOvh1hvmBAmUqwOlTcB6WcX2hrU=	
SFslkdqcN688MhnRS9KQTQ==	
al08StsOleS+oeIIMVh9SA==

Avalanche Effect
	
kvrelYYJFG/4VB1TI/LxmJ/rhnQ=	
IpOcdRRT36PY0sc0edYbJQ==	
h3iPduAspD0vTCwrJ+FyPQ==

SHA-1
It is immediately seen that the use of SHA-1 HMAC is the second quickest and offers a substantial difference in the avalanche effect. The average speed was calculated to be 37,157,858.66666667, or about 37.15 milliseconds. The original hash (base-64 encoded) is  “nOvh1hvmBAmUqwOlTcB6WcX2hrU=” and the avalanche hash (same input except the second bit was changed) was calculated to be “kvrelYYJFG/4VB1TI/LxmJ/rhnQ=” and it is visible that they are vastly different. It is important to note that SHA-1 has been replaced by SHA-3 for a multitude of reasons, because of possible attacks being discovered in SHA-1, SHA-2, and MD5, although SHA-2 is still widely in use since it is still relatively secure. SHA-1 is discouraged in usage because of attacks, and is being phased out in lieu for its successors SHA-2 and SHA-3. 

MD5 HMAC:
It is immediately seen that the use of MD5 HMAC is the quickest and offers a huge difference in the avalanche effect. The average speed was calculated to be 1,134,756 nanoseconds, or about 1.13 milliseconds. The original hash (base-64 encoded) is “SFslkdqcN688MhnRS9KQTQ==” and the avalanche hash (same input except the second bit was changed) was calculated to be “IpOcdRRT36PY0sc0edYbJQ==” and it is visible that they are vastly different. However, it is extremely important to note that MD5 is no longer in practical usage because it is broken since it is possible for hash collisions to occur, thus offers no practical security against crypt-analytical attacks. So the use of MD5 HMACs is strongly discouraged.

AES HMAC:
It is immediately seen that the use of AES HMAC is the quickest and offers a huge difference in the avalanche effect. The average speed was calculated to be 713,549,630.3333333 nanoseconds, or about 713 milliseconds. The original hash (base-64 encoded) is “al08StsOleS+oeIIMVh9SA==” and the avalanche hash (same input except the second bit was changed) was calculated to be “h3iPduAspD0vTCwrJ+FyPQ==”and it is visible that they are substantially different. AES is the HMAC that took the longest time to calculate, although still relatively fast (less than 1 second), and is the most secure out of the three HMACs being shown. AES is still commonly used throughout the web and is safe from attacks.

## Which HMAC to use:

If security is extremely important to your application, or even remotely needed, MD5 and SHA-1 should not be utilized. SHA-2 or SHA-3 should be utilized, the former will run quicker but the latter is much more secure. AES-based HMAC seems to be the clear winner in this scenario because it offers a lot of security, produces a strong avalanche effect and runs relatively quick (0.1 seconds). If security is not at all important to you at all and speed is absolutely vital to your usage of the program, then MD5 or SHA-1 can be utilized since they are much quicker than AES, but it is strongly discouraged to be used to due to security flaws. 

## Notes on Testing: 

For the sake of control, the input message was hardcoded and so was the encryption key for the AES-based HMAC (which was “kimchulisbestprofever”), and was opted to not utilize a GUI to ensure that the speed is tied only to the running of the algorithm and is not altered by any extra computation that occurs from using GUI elements. It should also be noted that Java was used, which was selected for its robust library that makes coding this much easier as opposed to hardcoding all the algorithms in, but comes at the cost of having a slower runtime than the programming language C. This is due to the fact that Java runs on a virtual machine (basically the java code gets transformed into JVM instructions, and the JVM instructions are then executed on the processor) whereas C compiles to native code (directly to code that the processor can run). The speed can be greatly influenced by the computer hardware that is being utilized by the machine (this test was run on a Intel Pentium N7300 which is extremely slow in comparison to cutting edge processors, as seen here http://www.cpubenchmark.net/cpu.php?cpu=Intel+Pentium+N3700+%40+1.60GHz the higher the number, the better the processor). This means in practical usage AES-based HMAC can be utilized since it offers a lot of security at an extremely good computation time. It should also be noted that in practical usage the issue of exchanging keys can arise, and can be solved via hardcoding the keys in, without ever showing the source code of the program, or using a SSL certificate, which has additional cost time but still results in a fast computation time.