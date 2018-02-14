 <template>
 	<div v-show="isActive">
	    <div style="max-height: 255px; overflow: auto;">
	        <ul class="list-group"> 
                <li class="list-group-item" v-for="livro in root.livros" :class="{'active': livro.checked }"> 
                    <div class="checkbox"> 
                        <input type="checkbox" v-model="livro.checked" :change="updateLivrosRemover(livro)"> 
                        {{ livro.name }} - <small>{{ livro.authorName }}</small> 
                        <span>id: {{livro.id}}</span>
                    </div>  
                </li> 
            </ul>
        </div>
        <edit-book-comp></edit-book-comp>
    </div>
 </template>
 
 <script>
 	/** componente para exibição e seleção de livros */
 	import C from './constants.vue'
 	import store from './store.vue'
 	import navmixin from './navigationmix.vue'
 	
 	var data = {
 		root: store.state,
 		componentName: C.components.ViewBooksComp 
 	}
 	
 	export default {
 		mixins: [navmixin],
 		data() {
 			return data;
 		},
 		methods: {
 			updateLivrosRemover(livro, event) {
				var livrosRemover = this.root.livrosRemover;
				var found = livrosRemover.find(function(l) {
					return livro.id == l.id;
				})
				if(livro.checked) {
					if(!found) {
						livrosRemover.push(livro);
					}
				} else if(found) {
					livrosRemover.splice(livrosRemover.indexOf(found), 1);
				}
			}
		},
		mounted: function() {
			store.dispatch('refreshLivros')
		}
 	}
 </script>
 