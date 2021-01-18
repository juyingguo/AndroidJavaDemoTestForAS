APP_STL := gnustl_static
APP_CPPFLAGS := -frtti -fexceptions

#APP_ABI是将要生成哪些cpu类型的so，all代表全部
APP_ABI := all
#APP_PLATFORM生成的so的最低android版本
APP_PLATFORM := android-14