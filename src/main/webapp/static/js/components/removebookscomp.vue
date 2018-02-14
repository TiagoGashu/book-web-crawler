 <template>
 	<div class="remove-book-comp-margin-top" v-show="isActive">
        <button class="btn btn-primary" type="button"@click="toggleRemoveAreaVisible">
            Toogle remove Area
        </button>
        <transition name="fade">
            <div id="removeArea" style="text-align: center;" v-show="root.removeAreaVisible">
                <ul class="list-group">
                    <li class="list-group-item"> 
                        - Book{{plural}} to remove - 
                    </li>
                    <li class="list-group-item" v-for="livro in root.livrosRemover">
                        {{livro.name}} - {{livro.authorName}}
                    </li>
                </ul>
                <div v-show="root.livrosRemover.length > 0" 
                        style="margin-top: 6px; position: absolute; right: 0; margin-right: 15px;">
                    <div class="col-sm col-sm-offset-10">
                        <button@click="removeBooks" class="js-remove btn btn-primary"
                          type="button">Remove Book{{plural}}</button>
                    </div>
                </div>
            </div>
        </transition>
    </div>
 </template>
 
 <script>
 	/** componente para remoção de livros */
 	import C from './constants.vue'
 	import navigationmix from './navigationmix.vue'
 	import Vue from 'vue'
 	import store from './store.vue'
 	import services from './services.vue'
 	
 	var data = {
 		root: store.state,
		/** aparece junto com o componente de view */
		componentName: C.components.ViewBooksComp
 	}
 	
 	export default {
		mixins: [navigationmix],
		data: function() {
			return data;
		},
		methods: {
			toggleRemoveAreaVisible: function() {
				this.root.removeAreaVisible = !this.root.removeAreaVisible;
			},
			removeBooks: function() {
				var ids = [];
				var livrosRemover = this.root.livrosRemover;
				livrosRemover.forEach(function(livro) {
					ids.push(livro.id);
				})
				if(ids.length == 0) {
					return
				}
				var bookIdsParam = 'bookIds=' + ids.join(',');
				services.removeBooks(bookIdsParam);
			}
		},
		computed: {
			plural: function() {
				if(this.root.livrosRemover.length > 1) {
					return 's';
				} else {
					return '';
				}
			}
		}
	}
</script>

<style>
	.remove-book-comp-margin-top{
		margin-top: 16px;
	}
</style>