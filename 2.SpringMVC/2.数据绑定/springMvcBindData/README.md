在SpringMVC后台控制层获取参数的方式主要有两种，一种是request.getParameter("name")，另外一种是用注解@RequestParam直接获取。
```java
public String filesUpload(@RequestParam(value="aa", required=true) String inputStr,   
        @RequestParam(value="inputInt", required=false) int inputInt  
        ,HttpServletRequest request)
```
