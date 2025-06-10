<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
					<el-col v-if="user_group == '管理员' || $check_figure('/report_statistics/table')" :span="8">
						<div class="card chart">
							<newLineChart v-if="line_obj_report_statistics.values.length > 0" id="line_obj_report_statistics" :vm="line_obj_report_statistics" :title="'报表统计统计'">
							</newLineChart>
							<div v-if="!line_obj_report_statistics.values.length">报表统计没有符合条件的数据</div>
						</div>
					</el-col>
					<el-col v-if="user_group == '管理员' || $check_figure('/vaccine_statistics/table')" :span="8">
						<div class="card chart">
							<newBarChart v-if="bar_obj_vaccine_statistics.values.length > 0" id="bar_obj_vaccine_statistics" :vm="bar_obj_vaccine_statistics" :title="'疫苗统计统计'">
							</newBarChart>
							<div v-if="!bar_obj_vaccine_statistics.values.length">疫苗统计没有符合条件的数据</div>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				isAdmin: false,
				recognitionType: "",
				activeName: "third",
				line_obj_report_statistics:{
					names:[],
					xAxis: [],
					values:[]
				},
				bar_obj_vaccine_statistics: {
					names:[],
					xAxis: [],
					values:[]
				},
				url_user_count: "~/api/user/count?",
			};
		},
		created() {
			this.getUserInfo();
			// 执行报表统计数据获取
			this.get_list_report_statistics();
			// 执行疫苗统计数据获取
			this.get_list_vaccine_statistics();
			
			
		},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
			// 获取报表统计统计折线图
			async get_list_report_statistics() {
				let group_by_value = "vaccinated_population";
				let data = {};
				let flag = false;
				await this.$get(
					"~/api/report_statistics/get_list?groupby="+group_by_value,data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let name_list = [];
							for (let i=0;i<list.length;i++){
								name_list.push(list[i].vaccinated_population);
							}
							this.line_obj_report_statistics.names = name_list;
							this.get_list_report_statistics_sub("vaccinated_population",flag);
						}
				});
			},
			async get_list_report_statistics_sub(v1,names_flag) {
				let data = {};
				let flag = false;
				await this.$get(
					"~/api/report_statistics/get_list?groupby=date",data,
					(json) => {
						if (json.result) {
							let list = json.result.list;
							let xAxis_list = [];
							for (let i=0;i<list.length;i++){
								xAxis_list.push(this.$toTime(list[i].date ,"yyyy-MM-dd"));
							}
							this.line_obj_report_statistics.xAxis = xAxis_list;
							this.get_list_report_statistics_sub_sub(v1,"date",names_flag,flag);
						}
				});
			},
			async get_list_report_statistics_sub_sub(v1,v2,names_flag,xAxis_flag) {
				let data_str = "{\""+v1+"\":\"\",\""+v2+"\":\"\"}";
				let data = JSON.parse(data_str);
				for (let i=0;i<this.line_obj_report_statistics.xAxis.length;i++){
					let list = []
					for (let j=0;j<this.line_obj_report_statistics.names.length;j++){
						data[v2] = this.line_obj_report_statistics.xAxis[i];
						data[v1] = this.line_obj_report_statistics.names[j];
						await this.$get(
								"~/api/report_statistics/sum?field=inoculation_quantity",
								data,
								(json) => {
									if (json.result) {
										list[j] = json.result;
									}else {
										list[j] = 0;
									}
								});
					}
					this.line_obj_report_statistics.values.push(list)
				}
				if (names_flag){
					this.get_nickname(this.line_obj_report_statistics.names,true);
				}
				if (xAxis_flag){
					this.get_nickname(this.line_obj_report_statistics.xAxis,true);
				}
			},
			// 获取疫苗统计统计柱状图
			async get_list_vaccine_statistics() {
				let name_list = [];
				let query_str = "";
				let group_by_value = "vaccine_name";
				let flag = false;
				let date_flag = "其他"
				name_list.push("库存数量");
				query_str = query_str+"inventory_quantity"+","
				this.bar_obj_vaccine_statistics.names = name_list
				query_str = query_str.substr(0,query_str.length-1);
				let data = {};
				await this.$get(
						"~/api/vaccine_statistics/bar_group?field="+query_str+"&groupby="+group_by_value,
						data,
						(json) => {
							if (json.result) {
								let xAxis = [];
								let values = [];
								json.result.list.map((o) => {
									if (date_flag === "日期") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd"));
									}else if (date_flag === "时间") {
										xAxis.push(this.$toTime(o[0] ,"yyyy-MM-dd hh:mm:ss"));
									}else {
										xAxis.push(o[0]);
									}
									values.push(o.splice(1))
								});
								this.bar_obj_vaccine_statistics.xAxis = xAxis;
								this.bar_obj_vaccine_statistics.values = values;
							}
							if (flag){
								this.get_nickname(this.bar_obj_vaccine_statistics.xAxis,true);
							}
						});
			},
				getUserInfo(){
					let userGroup = window.localStorage.getItem('user_group');
						if(userGroup){
						let _userGroup =JSON.parse(userGroup);
						let _info = JSON.parse(_userGroup.value);
						this.isAdmin = _info["user_group"] == "管理员" ? true : false;
					}
			}

		},
		computed:{
			recognitionHeight(){
				if(this.recognitionType === "face"){
					return "1070px"
				}else{
					return "1180px"
				}
			},
			recognitionUrl(){
					if(this.recognitionType === "face"){
					return "https://www.sk-ai.com/Experience/face-compare"
				}else{
					return "https://www.sk-ai.com/Experience/recognition?type="
				}
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}
	
	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
	width: 100%;
	height: 100%;
}
.iframe_box_top{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100px;
	font-size: 25px;
	line-height: 100px;
	background: #fff;
	z-index: 99999999;
	padding-left: 50px;
}
</style>
