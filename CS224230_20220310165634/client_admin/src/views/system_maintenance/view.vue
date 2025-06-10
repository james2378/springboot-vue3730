<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','maintenance_time') || $check_field('add','maintenance_time') || $check_field('set','maintenance_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="维护时间" prop="maintenance_time">
					<el-date-picker :disabled="disabledObj['maintenance_time_isDisabled']" v-if="user_group === '管理员' || (form['system_maintenance_id'] && $check_field('set','maintenance_time')) || (!form['system_maintenance_id'] && $check_field('add','maintenance_time'))" id="maintenance_time"
						v-model="form['maintenance_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','maintenance_time')">{{form['maintenance_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','maintenance_reason') || $check_field('add','maintenance_reason') || $check_field('set','maintenance_reason')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="维护原因" prop="maintenance_reason">
					<el-input id="maintenance_reason" v-model="form['maintenance_reason']" placeholder="请输入维护原因"
							  v-if="user_group === '管理员' || (form['system_maintenance_id'] && $check_field('set','maintenance_reason')) || (!form['system_maintenance_id'] && $check_field('add','maintenance_reason'))" :disabled="disabledObj['maintenance_reason_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','maintenance_reason')">{{form['maintenance_reason']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','operator') || $check_field('add','operator') || $check_field('set','operator')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="操作人" prop="operator">
					<el-input id="operator" v-model="form['operator']" placeholder="请输入操作人"
							  v-if="user_group === '管理员' || (form['system_maintenance_id'] && $check_field('set','operator')) || (!form['system_maintenance_id'] && $check_field('add','operator'))" :disabled="disabledObj['operator_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','operator')">{{form['operator']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['system_maintenance_id'] && $check_field('set','remarks')) || (!form['system_maintenance_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','maintenance_log') || $check_field('add','maintenance_log') || $check_field('set','maintenance_log')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="维护日志" prop="maintenance_log">
					<el-input type="textarea" id="maintenance_log" v-model="form['maintenance_log']" placeholder="请输入维护日志"
						v-if="user_group === '管理员' || (form['system_maintenance_id'] && $check_field('set','maintenance_log')) || (!form['system_maintenance_id'] && $check_field('add','maintenance_log'))" :disabled="disabledObj['maintenance_log_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','maintenance_log')">{{form['maintenance_log']}}</div>
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
				field: "system_maintenance_id",
				url_add: "~/api/system_maintenance/add?",
				url_set: "~/api/system_maintenance/set?",
				url_get_obj: "~/api/system_maintenance/get_obj?",
				url_upload: "~/api/system_maintenance/upload?",

				query: {
					"system_maintenance_id": 0,
				},

				form: {
					"maintenance_time":'', // 维护时间
					"maintenance_reason":'', // 维护原因
					"operator":'', // 操作人
					"remarks":'', // 备注
					"maintenance_log":'', // 维护日志
					"system_maintenance_id": 0, // ID

				},
				disabledObj:{
					"maintenance_time_isDisabled": false,
					"maintenance_reason_isDisabled": false,
					"operator_isDisabled": false,
					"remarks_isDisabled": false,
					"maintenance_log_isDisabled": false,
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
        if (this.form["maintenance_time"].indexOf("-")===-1){
          this.form["maintenance_time"] = this.$toTime(parseInt(this.form["maintenance_time"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["maintenance_time"]) > 9999){
					this.form["maintenance_time"] = this.$toTime(parseInt(this.form["maintenance_time"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/system_maintenance/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/system_maintenance/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/system_maintenance/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/system_maintenance/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/system_maintenance/view','get');
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
