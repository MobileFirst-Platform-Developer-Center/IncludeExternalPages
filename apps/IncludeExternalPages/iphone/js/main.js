/**
* Copyright 2015 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

function wlEnvInit(){
    wlCommonInit();
    
    WL.TabBar.init();
    
    WL.TabBar.addItem("WLtab1", function () {tabClicked(1); } ,"Home",{
    	image: "tabButton:Favorites"
    });

    WL.TabBar.addItem("WLtab2", function () {tabClicked(2); } ,"Client",{
    	image: "tabButton:Search"
    });
    
    WL.TabBar.addItem("WLtab3", function () {tabClicked(3); } ,"IBM",{
    	image: "tabButton:More"
    });

    WL.TabBar.setVisible(true);
    WL.TabBar.setSelectedItem("WLtab1");
    
    tabClicked(1);
}