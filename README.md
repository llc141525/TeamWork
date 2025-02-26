# TeamWork

> 做一个博客

首页类似于 b站 的信息流. 点击可以进入对应的文章.
不用登陆就可以看文章. 评论需要登录, 新建文章和删除文章需要管理员权限.
首页有文章分区
文章详情页有

- 阅读数, 评论数, 作者头像
- 顶部图片 (使用视差图完成 parallax)
- 文章内容 (后端渲染 markdown 发送给前端)
- 评论内容, 评论框

## 路由:

- `/` 首页
- `/blog-detail/page{}` 某篇文章的详细页面
- `/blog-create` 写文章

## 模型:

- 文章
    - 评论 (一对多), 评论的数量
    - 作者 (用户, 一对一)
    - 标题
    - 文本内容
    - 对应的图片
    - 观看数
    - 分区
- 评论
    - 内容
    - 评论者
- 用户
    - 权限(管理员?)
    - 用户名
    - 密码

## api:

- 文章
    - `/article` :获取所有文章 _GET_
    - `/article/{}` :获取指定文章 _GET_
    - `/article/create` :创建文章 _POST_
    - `/article/del/{}` :删除对应的文章 _DELETE_
    - `/article/{}` :更新文章 _PATCH_
- 评论
    - `/article/comment/{}` :获取某篇文章的评论 _GET_
    - `/article/comment/del/{}` :删除谋篇文章的评论 _DELETE_
- 用户
    - `/user` :获取所有用户 _GET_
    - `/user/{}` :获取指定 id 的用户 _GET_

### 技术栈:

- vuetify
- springboot
- mysql


