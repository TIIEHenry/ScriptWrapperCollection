#保持native方法不被混淆
#keepclasseswithmembernames 保留类和该类中所有带native限定符的方法
-keepclasseswithmembernames class * {
    native <methods>;
}