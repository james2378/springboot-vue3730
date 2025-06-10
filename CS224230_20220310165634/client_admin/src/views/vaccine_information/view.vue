<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccine_number') || $check_field('add','vaccine_number') || $check_field('set','vaccine_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="疫苗编号" prop="vaccine_number">
					<el-input id="vaccine_number" v-model="form['vaccine_number']" placeholder="请输入疫苗编号"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','vaccine_number')) || (!form['vaccine_information_id'] && $check_field('add','vaccine_number'))" :disabled="disabledObj['vaccine_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccine_number')">{{form['vaccine_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccine_name') || $check_field('add','vaccine_name') || $check_field('set','vaccine_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="疫苗名称" prop="vaccine_name">
					<el-input id="vaccine_name" v-model="form['vaccine_name']" placeholder="请输入疫苗名称"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','vaccine_name')) || (!form['vaccine_information_id'] && $check_field('add','vaccine_name'))" :disabled="disabledObj['vaccine_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccine_name')">{{form['vaccine_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','vaccine_effect') || $check_field('add','vaccine_effect') || $check_field('set','vaccine_effect')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="疫苗作用" prop="vaccine_effect">
					<el-input id="vaccine_effect" v-model="form['vaccine_effect']" placeholder="请输入疫苗作用"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','vaccine_effect')) || (!form['vaccine_information_id'] && $check_field('add','vaccine_effect'))" :disabled="disabledObj['vaccine_effect_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','vaccine_effect')">{{form['vaccine_effect']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adaptive_population') || $check_field('add','adaptive_population') || $check_field('set','adaptive_population')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="适应人群" prop="adaptive_population">
					<el-input id="adaptive_population" v-model="form['adaptive_population']" placeholder="请输入适应人群"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','adaptive_population')) || (!form['vaccine_information_id'] && $check_field('add','adaptive_population'))" :disabled="disabledObj['adaptive_population_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adaptive_population')">{{form['adaptive_population']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','taboo') || $check_field('add','taboo') || $check_field('set','taboo')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="禁忌" prop="taboo">
					<el-input id="taboo" v-model="form['taboo']" placeholder="请输入禁忌"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','taboo')) || (!form['vaccine_information_id'] && $check_field('add','taboo'))" :disabled="disabledObj['taboo_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','taboo')">{{form['taboo']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','manufacturer') || $check_field('add','manufacturer') || $check_field('set','manufacturer')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="生产厂家" prop="manufacturer">
					<el-input id="manufacturer" v-model="form['manufacturer']" placeholder="请输入生产厂家"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','manufacturer')) || (!form['vaccine_information_id'] && $check_field('add','manufacturer'))" :disabled="disabledObj['manufacturer_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','manufacturer')">{{form['manufacturer']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date_of_manufacture') || $check_field('add','date_of_manufacture') || $check_field('set','date_of_manufacture')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="生产日期" prop="date_of_manufacture">
					<el-date-picker :disabled="disabledObj['date_of_manufacture_isDisabled']" v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','date_of_manufacture')) || (!form['vaccine_information_id'] && $check_field('add','date_of_manufacture'))" id="date_of_manufacture"
						v-model="form['date_of_manufacture']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date_of_manufacture')">{{form['date_of_manufacture']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','expiration_date_') || $check_field('add','expiration_date_') || $check_field('set','expiration_date_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="过期日期" prop="expiration_date_">
					<el-date-picker :disabled="disabledObj['expiration_date__isDisabled']" v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','expiration_date_')) || (!form['vaccine_information_id'] && $check_field('add','expiration_date_'))" id="expiration_date_"
						v-model="form['expiration_date_']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','expiration_date_')">{{form['expiration_date_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','inventory_quantity') || $check_field('add','inventory_quantity') || $check_field('set','inventory_quantity')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="库存数量" prop="inventory_quantity">
					<el-input id="inventory_quantity" v-model="form['inventory_quantity']" placeholder="请输入库存数量"
							  v-if="user_group === '管理员' || (form['vaccine_information_id'] && $check_field('set','inventory_quantity')) || (!form['vaccine_information_id'] && $check_field('add','inventory_quantity'))" :disabled="disabledObj['inventory_quantity_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','inventory_quantity')">{{form['inventory_quantity']}}</div>
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
				field: "vaccine_information_id",
				url_add: "~/api/vaccine_information/add?",
				url_set: "~/api/vaccine_information/set?",
				url_get_obj: "~/api/vaccine_information/get_obj?",
				url_upload: "~/api/vaccine_information/upload?",

				query: {
					"vaccine_information_id": 0,
				},

				form: {
					"vaccine_number":'', // 疫苗编号
					"vaccine_name":'', // 疫苗名称
					"vaccine_effect":'', // 疫苗作用
					"adaptive_population":'', // 适应人群
					"taboo":'', // 禁忌
					"manufacturer":'', // 生产厂家
					"date_of_manufacture":'', // 生产日期
					"expiration_date_":'', // 过期日期
					"inventory_quantity":'', // 库存数量
					"vaccine_information_id": 0, // ID

				},
				disabledObj:{
					"vaccine_number_isDisabled": false,
					"vaccine_name_isDisabled": false,
					"vaccine_effect_isDisabled": false,
					"adaptive_population_isDisabled": false,
					"taboo_isDisabled": false,
					"manufacturer_isDisabled": false,
					"date_of_manufacture_isDisabled": false,
					"expiration_date__isDisabled": false,
					"inventory_quantity_isDisabled": false,
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
        if (this.form["date_of_manufacture"].indexOf("-")===-1){
          this.form["date_of_manufacture"] = this.$toTime(parseInt(this.form["date_of_manufacture"]),"yyyy-MM-dd")
        }
        if (this.form["expiration_date_"].indexOf("-")===-1){
          this.form["expiration_date_"] = this.$toTime(parseInt(this.form["expiration_date_"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["date_of_manufacture"]) > 9999){
					this.form["date_of_manufacture"] = this.$toTime(parseInt(this.form["date_of_manufacture"]),"yyyy-MM-dd")
				}
				if(parseInt(this.form["expiration_date_"]) > 9999){
					this.form["expiration_date_"] = this.$toTime(parseInt(this.form["expiration_date_"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/vaccine_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccine_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccine_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccine_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/vaccine_information/view','get');
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
