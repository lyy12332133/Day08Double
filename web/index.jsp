<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/19
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="JQ3.2.1.js"></script>
</head>
<body>
部门:
<select name="depart" id="s1">
    <option>---请选择---</option>
</select>
职务:
<select name="staff" id="s2">
    <option>---请选择---</option>
</select>

<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/showDepart.action",
                null,
                function (data) {
                    var _html = "<option>---请选择---</option>";
                    $.each(data, function (index, value) {
                        _html += '<option value="' + value.did + '">' + value.dname + '</option>'
                    });

                    $("#s1").html(_html);
                },
                "json");

        $("#s1").change(function () {
            $.post("${pageContext.request.contextPath}/showStaff.action",
                    {
                        did: $("#s1").val()
                    },
                    function (data) {
                        var _html = "<option>---请选择---</option>";
                        $.each(data, function (index, value) {
                            _html += '<option value="' + value.sid + '">' + value.sname + '</option>'
                        });
                        $("#s2").html(_html)
                    },
                    "json")
        })
    })
</script>
</body>
</html>
