/**
 * 手机发送短信验证码按钮的间隔操作
 * @type {number}
 * button -- 按钮
 *
 */
var wait = 60;// 时间
function sendTimeBtn(button, input, type) {// button为按钮的对象，这里是60秒过后，提示文字的改变
    if (wait == 0) {
        button.bind("click");
        button.removeAttr("disabled");
        button.css("background", "#428bca");
        button.html("获取验证码");// 改变按钮中value的值

        button.val("获取验证码");// 改变按钮中value的值
        wait = 120;
        if (type == 1) {
            input.removeAttr("readonly");
        }

    } else {
        button.unbind("click");
        button.attr("disabled", true);// 倒计时过程中禁止点击按钮
        button.val(wait + "秒后重新获取");// 改变按钮中value的值
        button.html(wait + "秒后重新获取");// 改变按钮中value的值
        button.css("background", "#CCC");
        if (type == 1) {
            input.attr("readonly", "readonly");
        }
        wait--;
        setTimeout(function () {
            sendTimeBtn(button, input, type);// 循环调用
        }, 1000);
    }
}
