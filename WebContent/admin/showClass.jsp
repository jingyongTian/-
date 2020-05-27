<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title>学生班级人数图表界面</title>
    <!-- 引入 echarts.js -->
    <script src="${ctx}/Card/js/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx}/Card/js/jquery-1.4.3.js"></script>
<%--     <script type="text/javascript" src="${ctx}/Card/js/H-ui.js"></script> --%>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 800px;height:500px;"></div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
//         Map map = (HashMap) request.getAttribute("map");
         var className = new Array(); 
//          分割数组
         className = "${className}".split(",");
//          去掉数组[ ] 符号
         className[0] = className[0].slice(1);
         className[className.length-1] = className[className.length-1].slice(0,-1);
         var num = new Array();
         num = "${num}".split(",");
         num[0] = num[0].slice(1);
         num[num.length-1] = num[num.length-1].slice(0,-1);
//          数组变为整型数组
         num = num.map(Number);
    
        // 指定图表的配置项和数据
   option = {
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            data: className,
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            type: 'bar',
            barWidth: '60%',
            data: num
        }
    ]
};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
     <td><input type="button" class="btn"  value="返回上一级" onclick="javascript:history.go(-1);"/></td>
     <tr><td><a href="admin/admin.jsp">返回主界面</a></td></tr>
</body>
</html>