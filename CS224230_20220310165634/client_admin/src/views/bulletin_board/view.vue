<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','announcement_title') || $check_field('add','announcement_title') || $check_field('set','announcement_title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="公告标题" prop="announcement_title">
					<el-input id="announcement_title" v-model="form['announcement_title']" placeholder="请输入公告标题"
							  v-if="user_group === '管理员' || (form['bulletin_board_id'] && $check_field('set','announcement_title')) || (!form['bulletin_board_id'] && $check_field('add','announcement_title'))" :disabled="disabledObj['announcement_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','announcement_title')">{{form['announcement_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publisher') || $check_field('add','publisher') || $check_field('set','publisher')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布人" prop="publisher">
					<el-input id="publisher" v-model="form['publisher']" placeholder="请输入发布人"
							  v-if="user_group === '管理员' || (form['bulletin_board_id'] && $check_field('set','publisher')) || (!form['bulletin_board_id'] && $check_field('add','publisher'))" :disabled="disabledObj['publisher_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','publisher')">{{form['publisher']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布日期" prop="release_date">
					<el-date-picker :disabled="disabledObj['release_date_isDisabled']" v-if="user_group === '管理员' || (form['bulletin_board_id'] && $check_field('set','release_date')) || (!form['bulletin_board_id'] && $check_field('add','release_date'))" id="release_date"
						v-model="form['release_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_date')">{{form['release_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['bulletin_board_id'] && $check_field('set','remarks')) || (!form['bulletin_board_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','announcement_content') || $check_field('add','announcement_content') || $check_field('set','announcement_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="公告内容" prop="announcement_content">
					<el-input type="textarea" id="announcement_content" v-model="form['announcement_content']" placeholder="请输入公告内容"
						v-if="user_group === '管理员' || (form['bulletin_board_id'] && $check_field('set','announcement_content')) || (!form['bulletin_board_id'] && $check_field('add','announcement_content'))" :disabled="disabledObj['announcement_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','announcement_content')">{{form['announcement_content']}}</div>
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
				field: "bulletin_board_id",
				url_add: "~/api/bulletin_board/add?",
				url_set: "~/api/bulletin_board/set?",
				url_get_obj: "~/api/bulletin_board/get_obj?",
				url_upload: "~/api/bulletin_board/upload?",

				query: {
					"bulletin_board_id": 0,
				},

				form: {
					"announcement_title":'', // 公告标题
					"publisher":'', // 发布人
					"release_date":'', // 发布日期
					"remarks":'', // 备注
					"announcement_content":'', // 公告内容
					"bulletin_board_id": 0, // ID

				},
				disabledObj:{
					"announcement_title_isDisabled": false,
					"publisher_isDisabled": false,
					"release_date_isDisabled": false,
					"remarks_isDisabled": false,
					"announcement_content_isDisabled": false,
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
        if (this.form["release_date"].indexOf("-")===-1){
          this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["release_date"]) > 9999){
					this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/bulletin_board/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/bulletin_board/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/bulletin_board/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/bulletin_board/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/bulletin_board/view','get');
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
