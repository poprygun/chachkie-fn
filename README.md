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
skaffold dev --default-repo harbor-repo.vmware.com/build_service --no-prune=false \
--cache-artifacts=false --port-forward \
```

skaffold dev --no-prune=false --cache-artifacts=false --port-forward

Generate rabbitmq service

```bash
kubectl run rabbitmq --image=rabbitmq:management --expose --port=15672 --restart=Never \
--dry-run=client -o yaml > k8s/rabbitmq.yml \
```

Test the endpoint using Http transport

```bash
echo '["one","two","three"]' | http -f POST :3000/chachkies Content-Type:text/plain
```

When using Spring Cloud Stream

Supplier function `names` will produce a flux of strings that will be processed by `chachkies` and further by `chachkiesSink`


## [Generate Tekton pipeline](https://github.com/GoogleContainerTools/skaffold/tree/master/examples/generate-pipeline)

## Resources

[Skaffold](https://skaffold.dev/)
[k8s dev tools](https://github.com/dsyer/skaffold-devtools-demo)