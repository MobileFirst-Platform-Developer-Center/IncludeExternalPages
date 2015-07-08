/*
*
    COPYRIGHT LICENSE: This information contains sample code provided in source code form. You may copy, modify, and distribute
    these sample programs in any form without payment to IBM® for the purposes of developing, using, marketing or distributing
    application programs conforming to the application programming interface for the operating platform for which the sample code is written.
    Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES,
    EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, SATISFACTORY QUALITY,
    FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT,
    INDIRECT, INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF THE SAMPLE SOURCE CODE.
    IBM HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE SOURCE CODE.

*/

function wlEnvInit(){
    wlCommonInit();

    WL.TabBar.init();
    
    WL.TabBar.addItem("WLtab1", function () {tabClicked(1); } ,"Home",{
    	image: "images/tab1.png",
    	imageSelected : "images/tab1.png"
    });

    WL.TabBar.addItem("WLtab2", function () {tabClicked(2); } ,"Client",{
    	image: "images/tab2.png",
    	imageSelected : "images/tab2.png"
    });
    
    WL.TabBar.addItem("WLtab3", function () {tabClicked(3); } ,"IBM",{
    	image: "images/tab3.png",
    	imageSelected : "images/tab3.png"
    });

    WL.TabBar.setVisible(true);
    
    tabClicked(1);
}