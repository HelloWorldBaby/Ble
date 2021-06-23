/**
 *@作者 luckly
 *@创建日期 2021/3/22 14:28
 *@描述  所有依赖
 */
object LibraryDependency {
	//buildGradle
	const val buildGradle = "com.android.tools.build:gradle:" + GradleVersion.gradleBuildVersion
	//路由
	const val gradleRouterRegister = "com.alibaba:arouter-register:" + GradleVersion.gradleRouterRegisterVersion

	//kotlin buildGradle
	const val gradleKotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + GradleVersion.gradleKotlinPluginVersion

	//约束布局
	const val constraintLayout = "androidx.constraintlayout:constraintlayout:" + LibraryVersions.constraintLayoutVersion

	//alibabaARouter
	const val alibabaARouter = "com.alibaba:arouter-api:" + LibraryVersions.alibabaARouterVersion
	const val alibabaARouterCompiler = "com.alibaba:arouter-compiler:" + LibraryVersions.alibabaARouterCompilerVersion
	const val alibabaFastjson = "com.alibaba:fastjson:" + LibraryVersions.alibabaFastjsonVersion + ".android"

	//multidex
	const val multidex = "androidx.multidex:multidex:" + LibraryVersions.multidexVersion

	//material
	const val material = "com.google.android.material:material:" + LibraryVersions.materialVersion

	//cardview
	const val cardview = "androidx.cardview:cardview:" + LibraryVersions.cardviewVersion

	//appcompat
	const val appcompat = "androidx.appcompat:appcompat:" + LibraryVersions.appcompatVersion

	//core
	const val core = "androidx.core:core:" + LibraryVersions.coreVersion

	//recyclerview
	const val recyclerview = "androidx.recyclerview:recyclerview:" + LibraryVersions.recyclerviewVersion

	//espresso
	const val espresso = "androidx.test.espresso:espresso-core:" + LibraryVersions.espressoVersion
	const val espressoResource = "androidx.test.espresso:espresso-idling-resource:" + LibraryVersions.espressoVersion

	//gson
	const val gson = "com.google.code.gson:gson:" + LibraryVersions.gsonVersion

	//glide 图片处理
	const val glide = "com.github.bumptech.glide:glide:" + LibraryVersions.glideVersion
	const val glideCompiler = "com.github.bumptech.glide:compiler:" + LibraryVersions.glideVersion
	const val glideOkHttp3Integration = "com.github.bumptech.glide:okhttp3-integration:" + LibraryVersions.glideVersion + "@aar"

	//recycleView 的适配器
	const val baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:" + LibraryVersions.baseRecyclerViewAdapterHelperVersion

	//rxBinding3
	const val rxBinding3 = "com.jakewharton.rxbinding3:rxbinding:" + LibraryVersions.rxBinding3Version

	//retrofit2
	const val retrofit2 = "com.squareup.retrofit2:retrofit:" + LibraryVersions.retrofit2Version
	const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:" + LibraryVersions.retrofit2Version
	const val retrofit2AdapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:" + LibraryVersions.retrofit2Version
	const val retrofit2Mock = "com.squareup.retrofit2:retrofit-mock:" + LibraryVersions.retrofit2Version

	//rxjava rxAndroid
	const val rxjava2 = "io.reactivex.rxjava2:rxjava:" + LibraryVersions.rxjava2Version
	const val rxjava2RxAndroid = "io.reactivex.rxjava2:rxandroid:" + LibraryVersions.rxjava2RxAndroidVersion

	//rxLifecycle2
	const val rxLifecycle2 = "com.trello.rxlifecycle2:rxlifecycle-android:" + LibraryVersions.rxLifecycle2Version
	const val rxLifecycle2Components = "com.trello.rxlifecycle2:rxlifecycle-components:" + LibraryVersions.rxLifecycle2Version
	const val rxLifecycle2ComponentsPreference = "com.trello.rxlifecycle2:rxlifecycle-components-preference:" + LibraryVersions.rxLifecycle2Version

	//okhttp3 拦截器
	const val okHttp3LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:" + LibraryVersions.okHttp3LoggingInterceptorVersion

	//内存检测
	const val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:" + LibraryVersions.leakCanaryAndroidVersion
	const val leakCanaryAndroidNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:" + LibraryVersions.leakCanaryAndroidVersion

	//测试包
	const val junit = "junit:junit:" + LibraryVersions.junitVersion

	//状态栏
	const val immersionBar = "com.gyf.immersionbar:immersionbar:" + LibraryVersions.immersionBarVersion
	const val immersionBarComponents = "com.gyf.immersionbar:immersionbar-components:" + LibraryVersions.immersionBarVersion

	//注解
	const val googleAutoService = "com.google.auto.service:auto-service:" + LibraryVersions.googleAutoServiceVersion
	const val javaPoet = "com.squareup:javapoet:" + LibraryVersions.javaPoetVersion

	//工具类
	const val viseUtils = "com.vise.xiaoyaoyou:viseutils:" + LibraryVersions.viseUtilsVersion
	//bugly
	const val buglyCrashReportUpGrade = "com.tencent.bugly:crashreport_upgrade:" + LibraryVersions.buglyCrashReportUpGradeVersion
	const val buglyNativeCrashReport = "com.tencent.bugly:nativecrashreport:" + LibraryVersions.buglyNativeCrashReportVersion

	//权限
	const val permissionX = "com.permissionx.guolindev:permissionx:" + LibraryVersions.permissionXVersion

	//androidx  annotation
	const val annotationS = "androidx.annotation:annotation:" + LibraryVersions.annotationVersion

	//support annotation
	const val supportAnnotations = "com.android.support:support-annotations:" + LibraryVersions.supportAnnotationsVersion

	//美团多渠道打包
	const val walleLibrary = "com.meituan.android.walle:library:" + LibraryVersions.walleLibraryVersion

	//单元测试
	const val mockitoCore = "org.mockito:mockito-core:" + LibraryVersions.mockitoCoreVersion
	const val robolectric = "org.robolectric:robolectric:" + LibraryVersions.robolectricVersion
	const val testRunner = "androidx.test:runner:" + LibraryVersions.testRunnerVersion
	const val roundedImageView = "com.makeramen:roundedimageview:" + LibraryVersions.roundedImageViewVersion
	const val legacySupportV4 = "androidx.legacy:legacy-support-v4:" + LibraryVersions.legacySupportV4Version
	const val verticalTabLayout = "q.rorbin:VerticalTabLayout:" + LibraryVersions.verticalTabLayoutVersion
	const val badgeView = "q.rorbin:badgeview:" + LibraryVersions.badgeViewVersion
	const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:" + LibraryVersions.exoplayerCoreVersion
	const val exoplayerExtensionMediaSession = "com.google.android.exoplayer:extension-mediasession:" + LibraryVersions.exoplayerCoreVersion
	const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:" + LibraryVersions.kotlinStdlibVersion
	const val danmakuFlameMaster = "com.github.ctiao:DanmakuFlameMaster:" + LibraryVersions.danmakuFlameMasterVersion

}
