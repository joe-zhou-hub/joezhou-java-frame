
**概念：** jwt内置负载项：

标识符|      全称 |           概述 |     备注
:---:|:---:      |:---:           |:---:
iss  |issuer     | token发行人     | 可由 `jwtBuilder.setIssuer()` 设置
iat  |issued at  | token发行时间   | 可由 `jwtBuilder.setIssuedAt()` 设置
sub  |subject    | token主题内容   | 可由 `jwtBuilder.setSubject()` 设置
aud  |audience   | token接收人     | 可由 `jwtBuilder.setAudience()` 设置
exp  |expiration | token过期时间戳 | 可由 `jwtBuilder.setExpiration()` 设置
nbf  |not before | token生效时间戳 | 可由 `jwtBuilder.setNotBefore()` 设置
jti  |jwt id     | token的id      | 可由 `jwtBuilder.setId()` 设置