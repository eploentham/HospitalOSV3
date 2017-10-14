<?xml version='1.0' encoding='TIS-620' ?>
<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN"
         "../dtd/helpset_2_0.dtd">

<helpset version="1.0">

  <!-- title -->
  <title>Help - Online</title>

  <!-- maps -->
  <maps>
     <homeID>top</homeID>
     <mapref location="Map.jhm"/>
  </maps>

  <!-- views -->
  <view>
    <name>TOC</name>
    <label>สารบัญ</label>
    <type>javax.help.TOCView</type>
    <data>SampleTOC.xml</data>
  </view>

  

  <presentation default="true" displayviewimages="false">
     <name>main window</name>
     <size width="600" height="500" />
     <location x="200" y="200" />
     <title>Report Center Help</title>
     <image>toplevelfolder</image>
     <toolbar>
	<helpaction>javax.help.BackAction</helpaction>
	<helpaction>javax.help.ForwardAction</helpaction>
	<helpaction>javax.help.SeparatorAction</helpaction>
	<helpaction>javax.help.HomeAction</helpaction>
	<helpaction>javax.help.ReloadAction</helpaction>
	<helpaction>javax.help.SeparatorAction</helpaction>
	<helpaction>javax.help.PrintAction</helpaction>
	<helpaction>javax.help.PrintSetupAction</helpaction>
     </toolbar>
  </presentation>
  <presentation>
     <name>main</name>
     <size width="500" height="550" />
     <location x="200" y="200" />
     <title>Report Center Help</title>
  </presentation>
</helpset>