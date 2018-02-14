<script>
	import store from '../vuex/store.vue'

	export default {
		computed: {
    		isActive() {
    			var activeItem = store.state.navigation.getActiveItem();
    			return activeItem && this.componentName == activeItem.componentName;
    		}
    	}
	}
</script>