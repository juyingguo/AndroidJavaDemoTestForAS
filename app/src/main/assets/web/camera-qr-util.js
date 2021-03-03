function startQrCodeScan() {
		// alert("123");
		javascript:javaJsInterfaceQr.startQrCodeScan();
	};
	function jsStartQrCodeScanWithPopupWindow() {
		// alert("123");
		javascript:javaJsInterfaceQr.startQrCodeScanWithPopupWindow();
	};
	function jsStartQrCodeScanWithDialog() {
		// alert("123");
		javascript:javaJsInterfaceQr.startQrCodeScanWithDialog();
	};
	function jsStartQrCodeScanWithDialogParam() {
		// alert("123");
		javascript:javaJsInterfaceQr.startQrCodeScanWithDialogParam(600,100);
	};
	function showQrCodeScan(result) {
		document.getElementById("id_qr_result").innerHTML = result;

	};
	function showQrCodeScan2() {
		document.getElementById("id_qr_result").innerHTML = "qr ok";

	};
	function jsDismissDialogBySendBroadCast(){
		javascript:dismiss_dialog_by_send_broadcast.dismissDialogBySendBroadCast();
	}