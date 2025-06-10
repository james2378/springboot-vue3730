<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccine_name') || $check_field('add','vaccine_name') || $check_field('set','vaccine_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="疫苗名称" prop="vaccine_name">
					<el-input id="vaccine_name" v-model="form['vaccine_name']" placeholder="请输入疫苗名称"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','vaccine_name')) || (!form['vaccination_appointment_id'] && $check_field('add','vaccine_name'))" :disabled="disabledObj['vaccine_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccine_name')">{{form['vaccine_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','manufacturer') || $check_field('add','manufacturer') || $check_field('set','manufacturer')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="生产厂家" prop="manufacturer">
					<el-input id="manufacturer" v-model="form['manufacturer']" placeholder="请输入生产厂家"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','manufacturer')) || (!form['vaccination_appointment_id'] && $check_field('add','manufacturer'))" :disabled="disabledObj['manufacturer_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','manufacturer')">{{form['manufacturer']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adaptive_population') || $check_field('add','adaptive_population') || $check_field('set','adaptive_population')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="适应人群" prop="adaptive_population">
					<el-input id="adaptive_population" v-model="form['adaptive_population']" placeholder="请输入适应人群"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','adaptive_population')) || (!form['vaccination_appointment_id'] && $check_field('add','adaptive_population'))" :disabled="disabledObj['adaptive_population_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adaptive_population')">{{form['adaptive_population']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','taboo') || $check_field('add','taboo') || $check_field('set','taboo')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="禁忌" prop="taboo">
					<el-input id="taboo" v-model="form['taboo']" placeholder="请输入禁忌"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','taboo')) || (!form['vaccination_appointment_id'] && $check_field('add','taboo'))" :disabled="disabledObj['taboo_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','taboo')">{{form['taboo']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('set','time_of_appointment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约时间" prop="time_of_appointment">
					<el-date-picker :disabled="disabledObj['time_of_appointment_isDisabled']" v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','time_of_appointment')) || (!form['vaccination_appointment_id'] && $check_field('add','time_of_appointment'))" id="time_of_appointment"
						v-model="form['time_of_appointment']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','time_of_appointment')">{{form['time_of_appointment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','end_time') || $check_field('add','end_time') || $check_field('set','end_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="结束时间" prop="end_time">
					<el-date-picker :disabled="disabledObj['end_time_isDisabled']" v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','end_time')) || (!form['vaccination_appointment_id'] && $check_field('add','end_time'))" id="end_time"
						v-model="form['end_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','end_time')">{{form['end_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_people_who_can_make_an_appointment') || $check_field('add','number_of_people_who_can_make_an_appointment') || $check_field('set','number_of_people_who_can_make_an_appointment')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="可预约人数" prop="number_of_people_who_can_make_an_appointment">
					<el-input id="number_of_people_who_can_make_an_appointment" v-model="form['number_of_people_who_can_make_an_appointment']" placeholder="请输入可预约人数"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','number_of_people_who_can_make_an_appointment')) || (!form['vaccination_appointment_id'] && $check_field('add','number_of_people_who_can_make_an_appointment'))" :disabled="disabledObj['number_of_people_who_can_make_an_appointment_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','number_of_people_who_can_make_an_appointment')">{{form['number_of_people_who_can_make_an_appointment']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccination_site') || $check_field('add','vaccination_site') || $check_field('set','vaccination_site')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="接种地点" prop="vaccination_site">
					<el-input id="vaccination_site" v-model="form['vaccination_site']" placeholder="请输入接种地点"
							  v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','vaccination_site')) || (!form['vaccination_appointment_id'] && $check_field('add','vaccination_site'))" :disabled="disabledObj['vaccination_site_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccination_site')">{{form['vaccination_site']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccine_effect') || $check_field('add','vaccine_effect') || $check_field('set','vaccine_effect')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="疫苗作用" prop="vaccine_effect">
					<el-input type="textarea" id="vaccine_effect" v-model="form['vaccine_effect']" placeholder="请输入疫苗作用"
						v-if="user_group === '管理员' || (form['vaccination_appointment_id'] && $check_field('set','vaccine_effect')) || (!form['vaccination_appointment_id'] && $check_field('add','vaccine_effect'))" :disabled="disabledObj['vaccine_effect_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccine_effect')">{{form['vaccine_effect']}}</div>
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
				field: "vaccination_appointment_id",
				url_add: "~/api/vaccination_appointment/add?",
				url_set: "~/api/vaccination_appointment/set?",
				url_get_obj: "~/api/vaccination_appointment/get_obj?",
				url_upload: "~/api/vaccination_appointment/upload?",

				query: {
					"vaccination_appointment_id": 0,
				},

				form: {
					"vaccine_name":'', // 疫苗名称
					"manufacturer":'', // 生产厂家
					"adaptive_population":'', // 适应人群
					"taboo":'', // 禁忌
					"time_of_appointment":'', // 预约时间
					"end_time":'', // 结束时间
					"number_of_people_who_can_make_an_appointment":'', // 可预约人数
					"vaccination_site":'', // 接种地点
					"vaccine_effect":'', // 疫苗作用
					"vaccination_appointment_id": 0, // ID

				},
				disabledObj:{
					"vaccine_name_isDisabled": false,
					"manufacturer_isDisabled": false,
					"adaptive_population_isDisabled": false,
					"taboo_isDisabled": false,
					"time_of_appointment_isDisabled": false,
					"end_time_isDisabled": false,
					"number_of_people_who_can_make_an_appointment_isDisabled": false,
					"vaccination_site_isDisabled": false,
					"vaccine_effect_isDisabled": false,
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
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd hh:mm:ss")
				this.form["end_time"] = this.$toTime(parseInt(this.form["end_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["time_of_appointment"]) > 9999){
					this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd hh:mm:ss")
				}
				if(parseInt(this.form["end_time"]) > 9999){
					this.form["end_time"] = this.$toTime(parseInt(this.form["end_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/vaccination_appointment/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccination_appointment/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccination_appointment/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccination_appointment/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccination_appointment/view','get');
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
