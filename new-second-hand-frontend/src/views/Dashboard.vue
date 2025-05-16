/* eslint-disable vue/multi-word-component-names */
<template>
  <div>
    <h2>数据可视化示例</h2>
    <div ref="chart" style="width: 600px; height: 400px;"></div>
  </div>
</template>
<script>
import * as echarts from 'echarts';
export default {
  name: 'UserDashboard',
  mounted() {
    const chart = echarts.init(this.$refs.chart);
    chart.setOption({
      title: { text: '用户注册统计' },
      tooltip: {},
      xAxis: { data: ['1月', '2月', '3月', '4月', '5月'] },
      yAxis: {},
      series: [{
        name: '注册用户数',
        type: 'bar',
        data: [5, 20, 36, 10, 10]
      }]
    });
  }
}
</script>