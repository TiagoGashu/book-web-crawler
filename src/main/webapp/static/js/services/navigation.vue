<script>
	import C from '../constants.vue'
	
	export default {
	    itens: [
	    	{name: 'Add Manga', componentName: C.components.AddManga, active: false},
	    	{name: 'View My Mangas', componentName: C.components.ViewMangas, active: false},
	    	{name: 'Add Book', componentName: C.components.AddBookComp, active: false}, 
	    	{name: 'View My Books', componentName: C.components.ViewBooksComp, active: false}
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