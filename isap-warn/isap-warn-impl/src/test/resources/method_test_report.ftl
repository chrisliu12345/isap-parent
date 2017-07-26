# ${serviceName} 测试报告

<#list methods as method>
## 方法名：${method.methodName}
方法描述：
**${method.methodDescription}**
<#if method.useLastResult>
${method.useLastResult?c}
**使用上个方法的返回值作为输入参数**
</#if>
<#if method.params??>
### 参数列表
<#list method.params as param>
<#if param??>
<#if param.param??>
<#if param.paramSize??>
列表长度：${param.paramSize}
</#if>
```json
${param.param}
```
</#if>
</#if>
</#list>
</#if>

<#if method.result??>
### 方法返回值
<#if method.resultSize??>
列表长度：${method.resultSize}
</#if>
```json
${method.result}
```
</#if>

</#list>