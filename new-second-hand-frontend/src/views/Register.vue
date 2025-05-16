<template>
  <div>
    <h2>用户注册</h2>
    <div>
      <button @click="mode='phone'" :disabled="mode==='phone'">手机号注册</button>
      <button @click="mode='email'" :disabled="mode==='email'">邮箱注册</button>
    </div>
    <form @submit.prevent="onRegister">
      <div v-if="mode==='phone'">
        <input v-model="form.phone" placeholder="手机号" required />
        <button type="button" @click="sendPhoneCode" :disabled="phoneCodeBtnDisabled">{{ phoneCodeBtnText }}</button>
      </div>
      <div v-if="mode==='email'">
        <input v-model="form.email" placeholder="邮箱（仅支持QQ邮箱）" required />
        <button type="button" @click="sendEmailCode" :disabled="emailCodeBtnDisabled">{{ emailCodeBtnText }}</button>
      </div>
      <input v-model="form.code" placeholder="验证码" required />
      <input v-model="form.password" type="password" placeholder="密码" required />
      <button type="submit">注册</button>
    </form>
    <p>{{ msg }}</p>
  </div>
</template>
<script>
import { register, sendPhoneCode, sendEmailCode } from '@/api/user'
export default {
  name: 'UserRegister',
  data() {
    return {
      mode: 'phone',
      form: { phone: '', email: '', code: '', password: '' },
      msg: '',
      phoneCodeBtnText: '获取验证码',
      phoneCodeBtnDisabled: false,
      emailCodeBtnText: '获取验证码',
      emailCodeBtnDisabled: false
    }
  },
  methods: {
    sendPhoneCode() {
      this.phoneCodeBtnDisabled = true;
      sendPhoneCode({ phone: this.form.phone }).then(res => {
        this.msg = res.data;
        this.phoneCodeBtnText = '已发送(60s)';
        let t = 60;
        const timer = setInterval(() => {
          t--;
          this.phoneCodeBtnText = `已发送(${t}s)`;
          if (t === 0) {
            clearInterval(timer);
            this.phoneCodeBtnText = '获取验证码';
            this.phoneCodeBtnDisabled = false;
          }
        }, 1000);
      }).catch(() => {
        this.phoneCodeBtnDisabled = false;
      });
    },
    sendEmailCode() {
      this.emailCodeBtnDisabled = true;
      sendEmailCode({ email: this.form.email }).then(res => {
        this.msg = res.data;
        this.emailCodeBtnText = '已发送(60s)';
        let t = 60;
        const timer = setInterval(() => {
          t--;
          this.emailCodeBtnText = `已发送(${t}s)`;
          if (t === 0) {
            clearInterval(timer);
            this.emailCodeBtnText = '获取验证码';
            this.emailCodeBtnDisabled = false;
          }
        }, 1000);
      }).catch(() => {
        this.emailCodeBtnDisabled = false;
      });
    },
    onRegister() {
      register({ ...this.form, mode: this.mode }).then(res => {
        if (res.data === '注册成功') {
          this.msg = '注册成功，请登录';
          this.$router.push('/login');
        } else {
          this.msg = res.data;
        }
      });
    }
  }
}
</script> 