<template>
	<div>
	    <ul class="nav nav-tabs">
            <li v-for="item in navigation.itens" class="nav-item">
                <a class="nav-link" :class="{'active': item.active}" @click="navigate(item, $event)">
                    {{item.name}}
                </a>
            </li>
	    </ul>
	</div>
</template>

<script>
	/** componente barra de navegacao */
	import C from '../constants.vue'
	import Vue from 'vue'
	import store from '../vuex/store.vue'
	
	export default {
		data() {
			return store.state
		},
		methods: {
			navigate: function(activeItem, event) {
				var navigation = this.navigation;
				navigation.itens.forEach(function(item) {
					item.active = false;
				})
				activeItem.active = true;
			}
		}
	}
</script>

<style>
	.nav-item:hover {
		cursor: pointer;
	}
</style>