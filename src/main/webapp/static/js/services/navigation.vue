<script>
	import C from '../constants.vue'
	
	export default {
	    itens: [
	    	{name: 'Add Book', componentName: C.components.AddBookComp, active: false}, 
	    	{name: 'View My Books', componentName: C.components.ViewBooksComp, active: false},
	    	// {name: 'Remove Books', componentName: C.components.RemoveBooksComp, active: false}
	    ],
	    activeItem: null,
	    getActiveItem: function() {
	    	if(this.activeItem != null && activeItem.active) {
	    		return this.activeItem;
	    	}
	    	
	    	var itens = this.itens;
	    	var active = null;
	    	itens.forEach(function(item) {
	    		if(active == null && item.active) {
	    			active = item;
	    		}
	    	});
	    	return active;
	    }
	}
</script>