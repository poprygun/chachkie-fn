# Spring Cloud Function

Experiment with kubernetes deployment of Spring Cloud Function application.

## Skaffoldize

Development cycle for local environment

```bash
skaffold dev --no-prune=false --cache-artifacts=false
```

`--no-prune=false --cache-artifacts=false` should delete images after ^C is pressed

Use [Harbor repository](https://harbor-repo.vmware.com/) 

```bash
skaffold dev --default-repo harbor-repo.vmware.com/build_service --no-prune=false --cache-artifacts=false --port-forward
```

harbor-repo.vmware.com/build_service


## [Generate Tekton pipeline](https://github.com/GoogleContainerTools/skaffold/tree/master/examples/generate-pipeline)

## Resources

[Skaffold](https://skaffold.dev/)