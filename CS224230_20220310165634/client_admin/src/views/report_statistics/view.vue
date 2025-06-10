<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccinated_population') || $check_field('add','vaccinated_population') || $check_field('set','vaccinated_population')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="接种人群" prop="vaccinated_population">
					<el-input id="vaccinated_population" v-model="form['vaccinated_population']" placeholder="请输入接种人群"
							  v-if="user_group === '管理员' || (form['report_statistics_id'] && $check_field('set','vaccinated_population')) || (!form['report_statistics_id'] && $check_field('add','vaccinated_population'))" :disabled="disabledObj['vaccinated_population_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccinated_population')">{{form['vaccinated_population']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','inoculation_quantity') || $check_field('add','inoculation_quantity') || $check_field('set','inoculation_quantity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="接种数量" prop="inoculation_quantity">
					<el-input id="inoculation_quantity" v-model="form['inoculation_quantity']" placeholder="请输入接种数量"
							  v-if="user_group === '管理员' || (form['report_statistics_id'] && $check_field('set','inoculation_quantity')) || (!form['report_statistics_id'] && $check_field('add','inoculation_quantity'))" :disabled="disabledObj['inoculation_quantity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','inoculation_quantity')">{{form['inoculation_quantity']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date') || $check_field('add','date') || $check_field('set','date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="日期" prop="date">
					<el-date-picker :disabled="disabledObj['date_isDisabled']" v-if="user_group === '管理员' || (form['report_statistics_id'] && $check_field('set','date')) || (!form['report_statistics_id'] && $check_field('add','date'))" id="date"
						v-model="form['date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date')">{{form['date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','statistician') || $check_field('add','statistician') || $check_field('set','statistician')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="统计人" prop="statistician">
					<el-input id="statistician" v-model="form['statistician']" placeholder="请输入统计人"
							  v-if="user_group === '管理员' || (form['report_statistics_id'] && $check_field('set','statistician')) || (!form['report_statistics_id'] && $check_field('add','statistician'))" :disabled="disabledObj['statistician_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','statistician')">{{form['statistician']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['report_statistics_id'] && $check_field('set','remarks')) || (!form['report_statistics_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "report_statistics_id",
				url_add: "~/api/report_statistics/add?",
				url_set: "~/api/report_statistics/set?",
				url_get_obj: "~/api/report_statistics/get_obj?",
				url_upload: "~/api/report_statistics/upload?",

				query: {
					"report_statistics_id": 0,
				},

				form: {
					"vaccinated_population":'', // 接种人群
					"inoculation_quantity":'', // 接种数量
					"date":'', // 日期
					"statistician":'', // 统计人
					"remarks":'', // 备注
					"report_statistics_id": 0, // ID

				},
				disabledObj:{
					"vaccinated_population_isDisabled": false,
					"inoculation_quantity_isDisabled": false,
					"date_isDisabled": false,
					"statistician_isDisabled": false,
					"remarks_isDisabled": false,
				},

			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["date"].indexOf("-")===-1){
          this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["date"]) > 9999){
					this.form["date"] = this.$toTime(parseInt(this.form["date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/report_statistics/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/report_statistics/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/report_statistics/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/report_statistics/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/report_statistics/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
