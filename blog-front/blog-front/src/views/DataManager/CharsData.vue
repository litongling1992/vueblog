<!--
 * @Author: your name
 * @Date: 2020-07-29 15:07:35
 * @LastEditTime: 2020-08-12 14:05:16
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\views\DataManager\CharsData.vue
-->
<template>
    <div class="charts-data">
        <el-tabs type="border-card" >
            <el-tab-pane label="折线图" name="line">
                <Charts :chartData="chartData" chartType="line"></Charts>
            </el-tab-pane>
            <el-tab-pane label="柱状图" name="bar">
                <Charts :chartData="chartData" chartType="bar"></Charts>
            </el-tab-pane>
            <el-tab-pane label="饼状图" name="pie">
                <Charts :chartData="chartData" chartType="pie"></Charts>
            </el-tab-pane>
        </el-tabs>

    </div>
</template>

<script lang="ts">
import { Component, Vue ,Provide} from 'vue-property-decorator';
import Charts from "../../components/Charts.vue";
@Component({
    components:{
        Charts,
    }
})
export default class CharsData extends Vue{
    @Provide() tabPosition: string = "line";
     @Provide() chartData: any = {
    xAxisData: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    yAxisData: [820, 932, 901, 934, 1290, 1330, 1320]
  };
}
</script>


<style scope>

</style>