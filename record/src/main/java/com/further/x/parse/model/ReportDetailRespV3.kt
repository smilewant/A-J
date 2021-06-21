package com.further.x.parse.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.math.BigDecimal

/**
 * create by FangQi on 2:13 PM, Thu, 1/7/21
 * qi.fang@aihuishou.com
 *
 * 描述：报告详情返回数据模型 V3
 **/
@Serializable
data class ReportDetailRespV3(
        val brandId : String = "",
        val categoryId : String = "",
        val deviceInfo: List<DeviceInfo> = listOf(), // 设备信息
        val deviceInfoShare: List< @Contextual Hardware> = listOf(), // 设备信息
        val facade: FunctionItems = FunctionItems(), // 成色外观
        val functionItems: FunctionItems = FunctionItems(), // 功能评估
        val hardware: List< @Contextual Hardware> = listOf(), // 硬件评估
        val imei: List<String>? = null,
        val inspectionSource: Int = 0, // 质检来源 1 盒子维修更换质检； 2 机大侠助手app质检 ；3 =1+2；4 手动询价 ；5 入库质检
        val inventoryState: Int = 0,
        val mainTitle: String = "",
        @Contextual
        val price: BigDecimal? = null, // 市场估价
        val repair: FunctionItems = FunctionItems(), // 维修情况
        val reportNo: String? = null,
        val remoteReportNo: String? = null,
        val sn: String? = null,
        @Contextual
        val suggestRecyclePrice: BigDecimal? = null, // 建议回收价
        val productId: Int? = null,
        val inspectionDate: Long = 0, // 报告日期
        val inspectionCase: InspectionCase = InspectionCase(), // 维修案例
        val platform: String = "", // 被检测手机平台 Android ios
        val images: List<Image> = listOf(), // 照片
        @Contextual
        val hbAmount: BigDecimal = BigDecimal(0), // 红包金额
        val score: Int? = null, // 验机评分
        val remark: String? = null, //备注
        val recycleOrderNo : String? = null, //回收单orderNo
        val recycleOrderStatus: Int? = null
//    val perfectPrice: BigDecimal? = null, // 完美价格
)
@Serializable
data class DeviceInfo(
        val ppnId: Int = 0,
        val state: Int = 0, // 内容状态 0 普通 1 突出的红色
        val content: String = "", // 内容
        val delaySeconds: Int = 0,
        val ppvItems: List<PpvItem> = listOf(),
        @Contextual
        val crashLog: IosCrashLogResp? = null,
        val tip: String? = null, // 是否有问号
        val key: String? = null, // 唯一键
        val title: String = "",
        val rectInfoPopVO: HardwareTipDialog? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceInfo

        if (ppnId != other.ppnId) return false
        if (tip != other.tip) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ppnId
        result = 31 * result + (tip?.hashCode() ?: 0)
        return result
    }
}
@Serializable
data class HardwareTipDialog(
        val batteryLife: String? = null, // 电池寿命
        val rechargeNumber: String? = null, // 充电次数
        val msg: String? = null // 异常原因
)

@Serializable
data class FunctionItems(
        val comment: String? = null,
        val jdxMark: Boolean = false,
        val ppn: List<FunctionItem> = listOf(),
        val stable: List< @Contextual Hardware> = listOf(),
)


@Serializable
data class Hardware(
        val desc: String = "",
        val batteryLife: String = "",
        val rechargeNumber: String? = null,
        val crashTime: String? = null,
        val crashReason: String? = null,
        val crashNum: String? = null,
        val factoryValue: String = "--",
        val factoryCompany: String? = null,
        val factoryDate: String? = null,
        val name: String = "",
        val readValue: String = "--",
        val readCompany: String? = null,
        val readDate: String? = null,
        val state: Int = 0,
        val tableIndex: Int = 0,
        val exceptionReason: String? = null,

        val crashLogResponseModel: IosCrashLogResp? = null,
        val crashLog: List<CrashLogVo> = listOf()
)

@Serializable
data class CrashLogVo(
        val crashType: String? = "",
        val crashTime: String? = "",
        val crashReason: String? = "",
        val crashNum: String? = "",
)

@Serializable
data class FunctionItem(
        val ppnId: Int = 0,
        val ppnName: String = "",
        val ppvList: List<PpvItem> = listOf(),
        val type: Int = 0 // ppn类型，0拍机堂PPN，1机大侠PPN
)

@Serializable
data class PpvItem(
        val groupKey: String = "",
        val checkedGrade: Int = CheckGrade.CHECK_GRADE_NONE.grade, // 选择状态等级 0浅黄色虚线 1正常黄色选中 2红色选中
        val checked: Boolean = true,
        val normal: Boolean = false,
        val ppvId: Int = 0,
        val ppnId: Int = 0,
        val name: String = "",
        val type: Int? = null // ppv类型，0拍机堂PPV，1机大侠PPV
) {
    enum class CheckGrade(val grade: Int) {
        CHECK_GRADE_NONE(-1),
        CHECK_GRADE_DASH_YELLOW(0),
        CHECK_GRADE_SOLID_YELLOW(1),
        CHECK_GRADE_SOLID_RED(2)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PpvItem

        if (groupKey != other.groupKey) return false
        if (ppvId != other.ppvId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = groupKey.hashCode()
        result = 31 * result + ppvId
        return result
    }

    override fun toString(): String {
        return "PpvItem(groupKey='$groupKey', checked=$checked, normal=$normal, ppvId=$ppvId, ppnId=$ppnId, name='$name', type=$type)"
    }
}
@Serializable
data class InspectionCase(
        val inspectionCount : String = "",
        val inspectionCaseList: List< @Contextual Hardware> = listOf(),
        val repairDetails: List<InspectionCaseDetail> = listOf()
)
@Serializable
data class InspectionCaseDetail(
        val time: String = "",

        val stable: List< @Contextual Hardware> = listOf()
)
@Serializable
data class Image(
        val image: String = "",
        val notice: String = ""
)
