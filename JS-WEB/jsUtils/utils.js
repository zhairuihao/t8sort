
//多么痛 的领悟 -.- =.= +.+



/**
 * ************************************************************************************************
 * 全局的AJAX访问，处理AJAX清求时SESSION超时 START
 * ************************************************************************************************
 */
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          // 通过XMLHttpRequest取得响应头，sessionstatus
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          loginUrl = XMLHttpRequest.getResponseHeader("location"); 
          if(sessionstatus=='00004'){
               // 这里怎么处理在你，这里跳转的登录页面
        	 // if( window.top != window.self ){
  				window.top.location = loginUrl;
  			// }
       }
    }
});
/**
 * ************************************************************************************************
 * 全局的AJAX访问，处理AJAX清求时SESSION超时 END
 * ************************************************************************************************
 */



/**
 * ************************************************************************************************
 * 处理数据字典翻译 START
 * ************************************************************************************************
 */
var dicMap={};
/**
 * 数得数据字典文本
 * 
 * @param code
 */
function getDicText(code,fun){
    $.ajax({
        type:"post",
        url:"dicList",
        data:{code:code},
        dataType:"json",
        async:false,
        success:function(data){
            fun(data);
        }
    });
}
 
/**
 * 缓数数据字典
 * 
 * @param value
 * @param rowData
 */
function cacheDic(value,code){
    if(dicMap[code]==undefined){
        getDicText(code,function(data){
            if((typeof data=='object')&&data.constructor==Array){
                var obj={};
                $(data).each(function(i,item){
                    if(item.value!="!"){
                        obj[item.value]=item.text;
                    }
                });
                dicMap[code]=obj;
            }
        });
    }
    if(dicMap[code][value]==undefined){
        return "";
    }
    return dicMap[code][value];
}
/**
 * 格式化数据字典
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 */
function formartDic(value,code){
    return cacheDic(value,code);
}

/**
 * ************************************************************************************************
 * 处理数据字典翻译 							END
 * ************************************************************************************************
 */




/**
 * ************************************************************************************************
 * 表格列单元格合并 							START
 * ************************************************************************************************
 */

(function ($) {  
    $.fn.extend({  
        // 表格合并单元格，colIdx要合并的列序号，从0开始
        "rowspan": function (colIdx) {  
            return this.each(function () {  
                var that;  
                $('tr', this).each(function (row) {  
                    $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {  
                        if (that != null && $(this).html() == $(that).html()) {  
                            rowspan = $(that).attr("rowSpan");  
                            if (rowspan == undefined) {  
                                $(that).attr("rowSpan", 1);  
                                rowspan = $(that).attr("rowSpan");  
                            }  
                            rowspan = Number(rowspan) + 1;  
                            $(that).attr("rowSpan", rowspan);  
                            $(this).hide();  
                        } else {  
                            that = this;  
                        }  
                    });  
                });  
            });  
        }  
    });  
  
})(jQuery); 
/**
 * ************************************************************************************************
 * 表格列单元格合并 								END
 * ************************************************************************************************
 */


/**
 * ************************************************************************************************
 * 日期格式化函数 								START
 * ************************************************************************************************
 */
Date.prototype.format = function(format) {  
    /*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */  
    var o = {  
        "M+" : this.getMonth() + 1, // month
        "d+" : this.getDate(), // day
        "h+" : this.getHours(), // hour
        "m+" : this.getMinutes(), // minute
        "s+" : this.getSeconds(), // second
        "q+" : Math.floor((this.getMonth() + 3)/3), // quarter
        "S" : this.getMilliseconds()  
        // millisecond
    }  
  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
                        - RegExp.$1.length));  
    }  
  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
                            ? o[k]  
                            : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}  
/**
 * ************************************************************************************************
 * 日期格式化函数 END
 * ************************************************************************************************
 */


/**
 * ************************************************************************************************
 * 日期计算周数 START
 * ************************************************************************************************
 */
function getYearWeek(date){  
    var date2=new Date(date.getFullYear(), 0, 1);  
    var day1=date.getDay();  
    if(day1==0) day1=7;  
    var day2=date2.getDay();  
    if(day2==0) day2=7;  
    d = Math.round((date.getTime() - date2.getTime()+(day2-day1)*(24*60*60*1000)) / 86400000);    
    return Math.ceil(d /7)+1;   
} ;
/**
 * ************************************************************************************************
 * 日期计算周数 END
 * ************************************************************************************************
 */




/**
 * ************************************************************************************************
 * 数组去重三种方法 第三种最快 START
 * ************************************************************************************************
 */

Array.prototype.unique1 = function(){
	 var res = [this[0]];
	 for(var i = 1; i < this.length; i++){
	  var repeat = false;
	  for(var j = 0; j < res.length; j++){
	   if(this[i]==res[j].AB){
	    repeat = true;
	    break;
	   }
	  }
	  if(!repeat){
	   res.push(this[i]);
	  }
	 }
	 return res;
}
Array.prototype.unique2 = function(){
	 this.sort(); // 先排序
	 var res = [this[0]];
	 for(var i = 1; i < this.length; i++){
	  if(this[i] !== res[res.length - 1]){
	   res.push(this[i]);
	  }
	 }
	 return res;
}
Array.prototype.unique3 = function(){
	 var res = [];
	 var json = {};
	 for(var i = 0; i < this.length; i++){
	  if(!json[this[i]]){
	   res.push(this[i]);
	   json[this[i]] = 1;
	  }
	 }
	 return res;
}
/**
 * ************************************************************************************************
 * 数组去重三种方法                             END
 * ************************************************************************************************
 */





/**
 * ************************************************************************************************
 * jqprint 实现网页局部打印                            START
 * ************************************************************************************************
 */
//<![CDATA[
function JQprint() {
	//Print ele4 with custom options
	$(".print").print({
		//Use Global styles
		globalStyles : true,
		//Add link with attrbute media=print
		mediaPrint : true,
		//Custom stylesheet
		//stylesheet : $('#basepath').val()+"css/login.jsp.css",
		//Print in a hidden iframe
		iframe : true,
		//Don't print this
		noPrintSelector : ".no-print"
	//Add this at top
	// prepend : "兰州市公安局<br/>",
	//Add this on bottom
	//append: '<br/><footer align="center">兰州市公安局</footer>'
	});
};
// Fork https://github.com/sathvikp/jQuery.print for the full list of options
//]]>
/**
 * ************************************************************************************************
 * jqprint 实现网页局部打印                            END
 * ************************************************************************************************
 */




/**
 * ************************************************************************************************
 * 导出多个表格到EXCEL或者ET                            START
 * ************************************************************************************************
 */
//JavaScript Document
//功能:导出多个表格到EXCEL或者ET
//调用方法：toExcel('要导出的表格ID,以|分隔多个表格','输出到excel中的工作薄名称','导出的方式，0为不带格式，1为带格式','要导出的列数')
var idTmr = "";
function Cleanup() {
	window.clearInterval(idTmr);
	CollectGarbage();
}

function toExcel(tableId, sheetname, method, cols) {
	if (!confirm("确认导出数据到EXCEL?")) {
		return false;
	}
	var tables = tableId.split("|");
	for (var n = 0; n < tables.length; n++) {
		if (!document.getElementById(tables[n])) {
			alert("表格" + tables[n] + "不存在,请检查是否有数据输出");
			return false;
		}
	}
	
	/***
	 *此处可增加浏览器的判断兼容主流浏览器 
	 */
	
	try {
		var oXL = new ActiveXObject("excel.Application");
	} catch (e1) {
		try {
			var oXL = new ActiveXObject("et.Application");
		} catch (e2) {
			alert(e2.description
					+ "\n\n\n要使用EXCEL对象，您必须安装Excel电子表格软件\n或者,需要安装Kingsoft ET软件\n\n同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。");
			return;
		}
	}

	try {
		var m = 1;
		oXL.Visible = true;
		oXL.ScreenUpdating = false;
		//oXL.Calculation=-4135;
		var oWB = oXL.Workbooks.Add;
		var oSheet = oWB.ActiveSheet;
		var xlsheet = oWB.Worksheets(1);
		for (var i = oWB.Worksheets.count; i > 1; i--) { //删除多余工作表
			oWB.Worksheets(i).Delete();
		}
		for (var n = 0; n < tables.length; n++) {
			var elTable = document.getElementById(tables[n]);
			var oRangeRef = document.body.createTextRange();
			oRangeRef.moveToElementText(elTable);
			oRangeRef.execCommand("Copy");
			oSheet.cells(m, 1).select;
			oSheet.Paste(); //此方式为直接粘贴，带格式
			if (method == 0) {
				oSheet.cells.ClearFormats;
				//以下删除因表头分拆后产生的空行,一般表头不会超过5行,此处检查5行数据
				for (var delrow = 1; delrow < 5; delrow++) {
					var isBlank = true;
					for (var col = 1; col <= elTable.rows[0].cells.length; col++) {
						if (oSheet.cells(m + 1, col).value != ""
								&& oSheet.cells(m + 1, col).value != undefined) {
							isBlank = false;
							break;
						}
					}
					if (isBlank) {
						oSheet.Rows(m + 1).Delete;
					}
				}
			}
			m += elTable.rows.length;
		}
		//oSheet.Cells.NumberFormatLocal = "@";//格式化数字时使用
		n = oSheet.Shapes.count;
		for (var i = 1; i <= n; i++) {
			oSheet.Shapes.Item(1).Delete(); //因为每次删除都会使总数减少,所以删除n次第一个对象,也可以倒过来从大到小删除
		}
		oXL.Selection.CurrentRegion.Select; //选择当前区域
		oXL.Selection.Interior.Pattern = 0; //设置底色为空
		oXL.Selection.Borders.LineStyle = 1; //设置单元格边框为实线
		oXL.Selection.ColumnWidth = 50; //设置列宽
		oXL.Selection.RowHeight = 16; //行高

		// oXL.Selection.Columns.AutoFit;                //列宽自动适应
		//xlsheet.Columns("A:Z").AutoFit;            //列宽自动适应
		xlsheet.Rows("1:" + m).AutoFit; //自动行高
		xlsheet.Name = sheetname;
		oSheet = null;
		oWB = null;
		appExcel = null;
		//oXL.Calculation=-4105;
		oXL.ScreenUpdating = true;
		idTmr = window.setInterval("Cleanup();", 1); //释放Excel进程，回收内存空间，避免产生多个不会自己终止的Excel进程
	} catch (e) {
		idTmr = window.setInterval("Cleanup();", 1);
		alert(e.description);
	}
}

/**
 * ************************************************************************************************
 * 导出多个表格到EXCEL或者ET                            END
 * ************************************************************************************************
 */