var exec = require('cordova/exec');

exports.videoCapture = function(arg0, success, error) {
    exec(success, error, "VideoCapture", "videoCapture", [arg0]);
};
