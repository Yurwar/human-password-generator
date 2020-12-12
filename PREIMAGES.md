#Preimages

1. Files for recovering: [MD5](/preimage/input/md5.csv), [SHA-1](/preimage/input/sha1.csv), [Bcrypt](/preimage/input/bcrypt.csv)
2. Instrument: [hashcat](https://hashcat.net/hashcat/)
3. Executing:
    
    * **MD5**:
      * Bruteforce [1, 16]: 
        Estimated time = more than 10K days. In result after 10 minutes of execution received 6339 recovered passwords (25,94%).
        
      * Dictionary ([15M of passwords](preimage/input/rockyou.txt)):
        Executed in 4 seconds with result of 9129 passwords (37.36%). 
        
      * Rules + Dictionary:
        Used multiple dictionaries (rockyou, top25, top100K) and multiple rule sets(best64, rockyou-rules).
        Estimated time = 4 days. After 30 minutes of execution received 10529 passwords (43.08%), which is best result.
        
    * **SHA-1**
      * Bruteforce [1, 16];
        Estimated time = more than 10K days. In result after 10 minutes of execution received 84 recovered passwords (0,08%).

      * Dictionary ([15M of passwords](preimage/input/rockyou.txt)):
        Estimated time = 3 hours. After 30 minutes of execution received 84437 passwords (84.44%), which is best result.

      * Rules + Dictionary:
        Estimated time = 21 days. Result similar with previous.

    * **Bcrypt**:
       * Bruteforce [1, 16];
         Hashcat can not estimate time for bruteforcing of bcrypt. In result after 10 minutes of execution received 0 recovered passwords.

       * Dictionary ([15M of passwords](preimage/input/rockyou.txt)):
         Estimated time = 386 years. After 10 minutes of execution received 417 passwords (0.42%), which is best result.

       * Rules + Dictionary:
         Estimated time = 21974. Result similar with previous. After 10 minutes of execution received 15 passwords (0.02%).
         
        ###Conclusion for methods
        On short time of run the best is **dictionary** method, which allows recover most common passwords in shortest time.
        All the methods has correlation between time and explored passwords. For each algorithm added file with best recovering result.
      
### Recommendations
* For storing passwords we should use hashing algorithms with high consumption of computing resources, like bcrypt or argon.
Quick algorithms like MD5 and SHA1 might be used only for some not sensitive operation. like hash-sum computation of file.

* Developers should enforce their users to use hard, different passwords(different symbols, numbers, cases). Ideally, random. Such passwords do not allow hackers to use dictionaries/patters/templates/rules.

    
        