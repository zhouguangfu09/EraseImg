## android图片橡皮擦功能和快速染色
****
###功能介绍
* 对图像染色，可以分别调整画壁大小和画壁的透明度
* 橡皮擦功能，其中橡皮擦的实现用了两个canvas,一个用于快速刷新（绘画轨迹），一另个是作用在ImageTouchView上显示用户最总的绘画结果。
****
####相关界面
* 原图

	![image](https://github.com/zhouguangfu09/EraseImg/blob/master/1.png)
	
* 画笔大小为32，透明度为255（不透明),如下图：

	![image](https://github.com/zhouguangfu09/EraseImg/blob/master/2.png)
	
* 画笔大小为32，透明度为10，如下图：

	![image](https://github.com/zhouguangfu09/EraseImg/blob/master/3.png)
	
* 部分擦除后：

	![image](https://github.com/zhouguangfu09/EraseImg/blob/master/4.png)
