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
	function jsStartQrCodeScanWithDialogParam(x,y,displaySizeType) {
		// alert("123");
		if(arguments.length == 2){
            javascript:javaJsInterfaceQr.startQrCodeScanWithDialogParam(x,y);
		}else if(arguments.length == 3){
            if(displaySizeType<=0)
                javascript:javaJsInterfaceQr.startQrCodeScanWithDialogParam(x,y,0);
            else if(displaySizeType >=1)
                javascript:javaJsInterfaceQr.startQrCodeScanWithDialogParam(x,y,1);
		}
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