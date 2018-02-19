<template>
	<div id="app" class="container main-container">
	    <h2 class="titulo">{{header}}</h2>
	    
	    <nav-bar></nav-bar>
	    
	    <add-manga></add-manga>
	    
	    <view-mangas></view-mangas>
	 
	    <add-book-comp></add-book-comp>
	
	    <view-books-comp></view-books-comp>
	 
	 	<alerts :alerts="alerts"></alerts>
	 	
	 	<modal-confirm :params="modalConfirm"></modal-confirm>
	 
	    <a id="copyright" href="http://www.freepik.com">Designed by starline / Freepik</a>
	</div>
</template>

<script>
import store from '../vuex/store.vue'

export default {
	data() {
		return store.state
	}
}
</script>

<style>
.main-container {
	position: relative;
	background: white;
	min-height: 100%;
}
.container {
	min-width: 350px;
    width: 40%; 
    margin-left: 20px auto;
    margin-right: 20px auto; 
}
#copyright {
	position: absolute;
    bottom: 0;
    font-size: 10pt;
    margin-left: 10px;
}
</style>
