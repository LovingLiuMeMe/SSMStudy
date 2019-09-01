<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
    <form role="form" action="addGoods" method="post">
        <div >
            <label>名称</label>
            <div>
                <input type="text" name="name" placeholder="请输入商品名称">
            </div>
        </div>
        <div>
            <label>价格</label>
            <div>
                <input type="text" name="price" placeholder="请输入商品价格">
            </div>
        </div>
        <div>
            <div>
                <button type="submit">提交</button>
            </div>
        </div>
    </form>
</body>
</html>
