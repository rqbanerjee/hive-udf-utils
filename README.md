= Hive UDF Utils

Hive User Defined Function Utilities

= Author

Rajat Banerjee

https://github.com/rqbanerjee/hive-udf-utils

= MD5

A quick and dirty md5 implementation that can be used with var args in Hive. More docs coming soon :)

= Usage Instructions

1. Fork from github: https://github.com/rqbanerjee/hive-udf-utils

2. mvn clean install

2a. OR use javac to compile it yourself

3. upload target/hive-udf-utils-1.0.jar to your hive host with SCP or similar

4. SSH to your hive host and launch 'hive' from the shell

5. add jar <path on remote host>/hive-udf-utils-1.0.jar

6. create temporary function md5 as com.hindoogle.hive.udf.util.MD5

6. select md5(1,2,3,4)....

= Requirements

* Access to Cloudera public repos

* Maven build env (optional, you can compile with javac and manually create a jar)

= Output

A Maven 'shaded' jar containing all of the hive and hadoop dependencies.